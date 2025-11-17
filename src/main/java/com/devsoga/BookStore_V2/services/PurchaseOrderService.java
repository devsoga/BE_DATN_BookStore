package com.devsoga.BookStore_V2.services;

import com.devsoga.BookStore_V2.dtos.requests.PurchaseOrderRequest;
import com.devsoga.BookStore_V2.dtos.responses.PurchaseOrderDetailResponse;
import com.devsoga.BookStore_V2.dtos.responses.PurchaseOrderResponse;
import com.devsoga.BookStore_V2.enties.PurchaseOrderDetailEntity;
import com.devsoga.BookStore_V2.enties.PurchaseOrderEntity;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.repositories.PurchaseOrderDetailRepository;
import com.devsoga.BookStore_V2.repositories.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private PurchaseOrderDetailRepository purchaseOrderDetailRepository;
    
    @Autowired
    private com.devsoga.BookStore_V2.repositories.EmployeeRepository employeeRepository;

    @Autowired
    private com.devsoga.BookStore_V2.repositories.SupplierRepository supplierRepository;
    
    @Autowired
    private com.devsoga.BookStore_V2.repositories.ProductRepository productRepository;

    @Autowired
    private com.devsoga.BookStore_V2.repositories.PromotionRepository promotionRepository;
    
    @Autowired
    private com.devsoga.BookStore_V2.repositories.InventoryRepository inventoryRepository;

    @Autowired
    private com.devsoga.BookStore_V2.repositories.InventoryDetailRepository inventoryDetailRepository;

    public BaseRespone getAll() {
        BaseRespone resp = new BaseRespone();
        try {
            List<PurchaseOrderEntity> list = purchaseOrderRepository.findAll();
            // by default do not include full product details in responses
            List<PurchaseOrderResponse> data = list.stream().map(e -> toResp(e, false)).collect(Collectors.toList());
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(data);
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone getByCode(String importInvoiceCode) {
        BaseRespone resp = new BaseRespone();
        try {
            Optional<PurchaseOrderEntity> opt = purchaseOrderRepository.findByImportInvoiceCode(importInvoiceCode);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Not found");
                resp.setData(null);
                return resp;
            }
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(toResp(opt.get(), false));
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    /**
     * Create inventory and inventory_detail records for the given purchase order.
     * This method is safe to call multiple times; it avoids creating duplicate inventory_detail codes.
     */
    private void createInventoryDetailsForInvoice(PurchaseOrderEntity saved) {
        if (saved == null) return;
        if (saved.getImportInvoiceDetailList() == null) return;
        for (PurchaseOrderDetailEntity d : saved.getImportInvoiceDetailList()) {
            try {
                if (d == null || d.getProductEntity() == null) continue;
                String productCode = d.getProductEntity().getProductCode();
                String invCode = "INV_" + productCode;

                var invOpt = inventoryRepository.findByInventoryCode(invCode);
                com.devsoga.BookStore_V2.enties.InventoryEntity invEnt;
                if (invOpt.isPresent()) {
                    invEnt = invOpt.get();
                } else {
                    // create inventory row if missing
                    invEnt = new com.devsoga.BookStore_V2.enties.InventoryEntity();
                    invEnt.setInventoryCode(invCode);
                    invEnt.setStatus(true);
                    invEnt.setProductEntity(d.getProductEntity());
                    invEnt = inventoryRepository.save(invEnt);
                }

                com.devsoga.BookStore_V2.enties.InventoryDetailEntity idEnt = new com.devsoga.BookStore_V2.enties.InventoryDetailEntity();
                // generate a code if not present
                String idCode = d.getImportInvoiceDetailCode() != null ? "INVDET_" + d.getImportInvoiceDetailCode() : ("INVDET_" + saved.getImportInvoiceCode() + "_" + productCode);
                idEnt.setInventoryDetailCode(idCode);
                idEnt.setInventoryEntity(invEnt);
                idEnt.setImportInvoiceEntity(saved);
                idEnt.setImportInvoiceDetailEntity(d);
                // Set quantity = quantity_imported - quantity_sold - cancelled_quantity
                int quantityImported = d.getQuantityImported() == null ? 0 : d.getQuantityImported();
                int quantitySold = d.getQuantitySold() == null ? 0 : d.getQuantitySold();
                int cancelledQty = d.getCancelledQuantity() == null ? 0 : d.getCancelledQuantity();
                idEnt.setQuantity(quantityImported - quantitySold - cancelledQty);

                // avoid duplicate inventory detail code
                if (inventoryDetailRepository.findByInventoryDetailCode(idEnt.getInventoryDetailCode()).isEmpty()) {
                    inventoryDetailRepository.save(idEnt);
                }
            } catch (Exception inner) {
                // continue creating other details even if one fails
            }
        }
    }

    /**
     * Update inventory_detail quantities when quantity_sold changes in import_invoice_detail.
     * This method finds all inventory_detail records linked to the given import_invoice_detail
     * and updates their quantity to be quantity_imported - quantity_sold.
     */
    public void updateInventoryDetailQuantities(PurchaseOrderDetailEntity importInvoiceDetail) {
        if (importInvoiceDetail == null) return;
        try {
            // Find inventory_detail records linked to this import_invoice_detail
            String detailCode = importInvoiceDetail.getImportInvoiceDetailCode();
            if (detailCode != null) {
                var inventoryDetails = inventoryDetailRepository.findByImportInvoiceDetailEntity_ImportInvoiceDetailCode(detailCode);
                
                int quantityImported = importInvoiceDetail.getQuantityImported() == null ? 0 : importInvoiceDetail.getQuantityImported();
                int quantitySold = importInvoiceDetail.getQuantitySold() == null ? 0 : importInvoiceDetail.getQuantitySold();
                int cancelledQty = importInvoiceDetail.getCancelledQuantity() == null ? 0 : importInvoiceDetail.getCancelledQuantity();
                int newQuantity = quantityImported - quantitySold - cancelledQty;
                
                for (var invDetail : inventoryDetails) {
                    invDetail.setQuantity(newQuantity);
                }
                
                if (!inventoryDetails.isEmpty()) {
                    inventoryDetailRepository.saveAll(inventoryDetails);
                }
            }
        } catch (Exception ex) {
            // Log error but don't throw - this is a background update
        }
    }

    public BaseRespone getByProductCode(String productCode) {
        BaseRespone resp = new BaseRespone();
        try {
        List<PurchaseOrderEntity> list = purchaseOrderRepository.findByProductCode(productCode);
        // Only include invoices which are APPROVED, then filter details to the requested product code
        List<PurchaseOrderResponse> data = list.stream()
            .filter(inv -> "APPROVED".equals(inv.getStatus()))
            .map(e -> toRespFiltered(e, productCode))
            .collect(Collectors.toList());
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(data);
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone create(PurchaseOrderRequest req) {
        BaseRespone resp = new BaseRespone(); 
        try {
            PurchaseOrderEntity e = new PurchaseOrderEntity();
            e.setImportInvoiceCode(req.getImportInvoiceCode());
            e.setDiscount(req.getDiscount());
            e.setStatus(req.getStatus() == null ? "PENDING" : req.getStatus());
            e.setTotalAmount(req.getTotalAmount());
            e.setDescription(req.getDescription());
            // set employee and supplier by code (load managed entities)
            if (req.getEmployeeCode() != null) {
                var empOpt = employeeRepository.findByEmployeeCode(req.getEmployeeCode());
                if (empOpt.isEmpty()) {
                    resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    resp.setMessage("Employee not found: " + req.getEmployeeCode());
                    resp.setData(null);
                    return resp;
                }
                e.setEmployeeEntity(empOpt.get());
            }
            if (req.getSupplierCode() != null) {
                var supOpt = supplierRepository.findBySupplierCode(req.getSupplierCode());
                if (supOpt.isEmpty()) {
                    resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    resp.setMessage("Supplier not found: " + req.getSupplierCode());
                    resp.setData(null);
                    return resp;
                }
                e.setSupplierEntity(supOpt.get());
            }

            // map details - check for existing detail codes to avoid duplicates
            if (req.getDetails() != null && !req.getDetails().isEmpty()) {
                List<PurchaseOrderDetailEntity> details = req.getDetails().stream().map(d -> {
                    // Check if detail code already exists
                    Optional<PurchaseOrderDetailEntity> existingDetail = 
                        purchaseOrderDetailRepository.findByImportInvoiceDetailCode(d.getImportInvoiceDetailCode());
                    
                    PurchaseOrderDetailEntity de;
                    if (existingDetail.isPresent()) {
                        // Update existing detail
                        de = existingDetail.get();
                        de.setQuantity(d.getQuantity());
                        de.setImportPrice(d.getImportPrice());
                        de.setTotalAmount(d.getTotalAmount());
                        if (d.getProductCode() != null) {
                            var productOpt = productRepository.findByProductCode(d.getProductCode());
                            if (productOpt.isEmpty()) {
                                throw new RuntimeException("Product not found: " + d.getProductCode());
                            }
                            de.setProductEntity(productOpt.get());
                        }
                    } else {
                        // Create new detail
                        de = new PurchaseOrderDetailEntity();
                        de.setImportInvoiceDetailCode(d.getImportInvoiceDetailCode());
                        de.setQuantity(d.getQuantity());
                        de.setImportPrice(d.getImportPrice());
                        de.setTotalAmount(d.getTotalAmount());
                        if (d.getProductCode() != null) {
                            var productOpt = productRepository.findByProductCode(d.getProductCode());
                            if (productOpt.isEmpty()) {
                                throw new RuntimeException("Product not found: " + d.getProductCode());
                            }
                            de.setProductEntity(productOpt.get());
                        }
                    }
                    de.setImportInvoiceEntity(e);
                    return de;
                }).collect(Collectors.toList());
                e.setImportInvoiceDetailList(details);
            }

            // reason if provided
            e.setReason(req.getReason());

            PurchaseOrderEntity saved = purchaseOrderRepository.save(e);

            // Only create inventory_detail rows when the invoice status is APPROVED
            try {
                if (saved.getStatus() != null && "APPROVED".equals(saved.getStatus())) {
                    createInventoryDetailsForInvoice(saved);
                }
            } catch (Exception ignore) {}
            resp.setStatusCode(HttpStatus.CREATED.value());
            resp.setMessage("Created");
            resp.setData(toResp(saved, false));
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone update(String importInvoiceCode, PurchaseOrderRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = purchaseOrderRepository.findByImportInvoiceCode(importInvoiceCode);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Not found");
                resp.setData(null);
                return resp;
            }
            PurchaseOrderEntity e = opt.get();
            if (req.getImportInvoiceCode() != null) e.setImportInvoiceCode(req.getImportInvoiceCode());
            if (req.getDiscount() != null) e.setDiscount(req.getDiscount());
            if (req.getStatus() != null) e.setStatus(req.getStatus());
            if (req.getReason() != null) e.setReason(req.getReason());
            if (req.getTotalAmount() != null) e.setTotalAmount(req.getTotalAmount());
            if (req.getDescription() != null) e.setDescription(req.getDescription());
            if (req.getEmployeeCode() != null) {
                var empOpt = employeeRepository.findByEmployeeCode(req.getEmployeeCode());
                if (empOpt.isEmpty()) {
                    resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    resp.setMessage("Employee not found: " + req.getEmployeeCode());
                    resp.setData(null);
                    return resp;
                }
                e.setEmployeeEntity(empOpt.get());
            }
            if (req.getSupplierCode() != null) {
                var supOpt = supplierRepository.findBySupplierCode(req.getSupplierCode());
                if (supOpt.isEmpty()) {
                    resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    resp.setMessage("Supplier not found: " + req.getSupplierCode());
                    resp.setData(null);
                    return resp;
                }
                e.setSupplierEntity(supOpt.get());
            }
            // isDeleted removed from schema; ignore any flag

            // NOTE: details handling here is simple: replace existing details if provided
            if (req.getDetails() != null) {
                // Merge details safely to avoid duplicate-key inserts:
                // 1) find existing details for this invoice
                List<PurchaseOrderDetailEntity> existing = e.getImportInvoiceDetailList();
                if (existing == null) existing = new java.util.ArrayList<>();

                // map existing by code
                java.util.Map<String, PurchaseOrderDetailEntity> existingByCode = existing.stream()
                        .filter(d -> d.getImportInvoiceDetailCode() != null)
                        .collect(Collectors.toMap(PurchaseOrderDetailEntity::getImportInvoiceDetailCode, d -> d));

                // codes present in request
                java.util.Set<String> reqCodes = req.getDetails().stream()
                        .map(d -> d.getImportInvoiceDetailCode())
                        .filter(java.util.Objects::nonNull)
                        .collect(Collectors.toSet());

                // orphaned = existing codes not present in request -> delete them first
                List<PurchaseOrderDetailEntity> orphaned = existing.stream()
                        .filter(d -> d.getImportInvoiceDetailCode() == null || !reqCodes.contains(d.getImportInvoiceDetailCode()))
                        .collect(Collectors.toList());
                if (!orphaned.isEmpty()) {
                    // delete from DB to avoid unique-key conflicts
                    purchaseOrderDetailRepository.deleteAll(orphaned);
                    // also remove from existing list
                    existing.removeAll(orphaned);
                }

                // build final list: update existing or create new
                List<PurchaseOrderDetailEntity> finalList = new java.util.ArrayList<>();
                for (var d : req.getDetails()) {
                    String code = d.getImportInvoiceDetailCode();
                    PurchaseOrderDetailEntity de = null;
                    if (code != null && existingByCode.containsKey(code)) {
                        de = existingByCode.get(code);
                        // update fields
                        de.setQuantity(d.getQuantity());
                        de.setImportPrice(d.getImportPrice());
                        de.setTotalAmount(d.getTotalAmount());
                        if (d.getProductCode() != null) {
                            var productOpt = productRepository.findByProductCode(d.getProductCode());
                            if (productOpt.isEmpty()) {
                                throw new RuntimeException("Product not found: " + d.getProductCode());
                            }
                            de.setProductEntity(productOpt.get());
                        }
                    } else {
                        de = new PurchaseOrderDetailEntity();
                        de.setImportInvoiceDetailCode(code);
                        de.setQuantity(d.getQuantity());
                        de.setImportPrice(d.getImportPrice());
                        de.setTotalAmount(d.getTotalAmount());
                        if (d.getProductCode() != null) {
                            var productOpt = productRepository.findByProductCode(d.getProductCode());
                            if (productOpt.isEmpty()) {
                                throw new RuntimeException("Product not found: " + d.getProductCode());
                            }
                            de.setProductEntity(productOpt.get());
                        }
                    }
                    de.setImportInvoiceEntity(e);
                    finalList.add(de);
                }

                // replace existing list contents in-place to keep JPA managed collection
                existing.clear();
                existing.addAll(finalList);
                e.setImportInvoiceDetailList(existing);
            }

            String oldStatus = e.getStatus();
            PurchaseOrderEntity saved = purchaseOrderRepository.save(e);

            // If status transitioned to APPROVED, create inventory details
            try {
                if ((oldStatus == null || !"APPROVED".equals(oldStatus)) && "APPROVED".equals(saved.getStatus())) {
                    createInventoryDetailsForInvoice(saved);
                }
            } catch (Exception ignore) {}

            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Updated");
            resp.setData(toResp(saved, false));
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone delete(String importInvoiceCode) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = purchaseOrderRepository.findByImportInvoiceCode(importInvoiceCode);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Not found");
                resp.setData(null);
                return resp;
            }
            PurchaseOrderEntity e = opt.get();
            // soft delete: set status = DELETED
            e.setStatus("DELETED");
            purchaseOrderRepository.save(e);
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Deleted (soft)");
            resp.setData(null);
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone approveById(Integer id) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = purchaseOrderRepository.findById(id);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Not found");
                resp.setData(null);
                return resp;
            }
            PurchaseOrderEntity e = opt.get();
            // mark approved
            e.setStatus("APPROVED");
            e.setReason(null);
            purchaseOrderRepository.save(e);
            // fetch fresh entity with details and create inventory details
            try {
                var fresh = purchaseOrderRepository.findById(id);
                if (fresh.isPresent()) createInventoryDetailsForInvoice(fresh.get());
            } catch (Exception ignore) {}
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Approved");
            resp.setData(null);
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone approveByCode(String importInvoiceCode) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = purchaseOrderRepository.findByImportInvoiceCode(importInvoiceCode);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Not found");
                resp.setData(null);
                return resp;
            }
            PurchaseOrderEntity e = opt.get();
            e.setStatus("APPROVED");
            purchaseOrderRepository.save(e);
            try {
                var fresh = purchaseOrderRepository.findByImportInvoiceCode(importInvoiceCode);
                if (fresh.isPresent()) createInventoryDetailsForInvoice(fresh.get());
            } catch (Exception ignore) {}
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Approved");
            resp.setData(null);
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone rejectById(Integer id, String reason) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = purchaseOrderRepository.findById(id);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Not found");
                resp.setData(null);
                return resp;
            }
            PurchaseOrderEntity e = opt.get();
            e.setStatus("REJECTED");
            e.setReason(reason);
            purchaseOrderRepository.save(e);
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Rejected");
            resp.setData(null);
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone rejectByCode(String importInvoiceCode, String reason) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = purchaseOrderRepository.findByImportInvoiceCode(importInvoiceCode);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Not found");
                resp.setData(null);
                return resp;
            }
            PurchaseOrderEntity e = opt.get();
            e.setStatus("REJECTED");
            e.setReason(reason);
            purchaseOrderRepository.save(e);
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Rejected");
            resp.setData(null);
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    private PurchaseOrderResponse toResp(PurchaseOrderEntity e, boolean includeProductDetails) {
        if (e == null) return null;
        PurchaseOrderResponse r = new PurchaseOrderResponse();
        r.setId(e.getId());
        r.setImportInvoiceCode(e.getImportInvoiceCode());
        r.setDiscount(e.getDiscount());
        r.setStatus(e.getStatus());
        r.setTotalAmount(e.getTotalAmount());
        r.setDescription(e.getDescription());
    if (e.getEmployeeEntity() != null) r.setEmployeeCode(e.getEmployeeEntity().getEmployeeCode());
    if (e.getSupplierEntity() != null) {
        r.setSupplierName(e.getSupplierEntity().getSupplierName());
        r.setSupplierCode(e.getSupplierEntity().getSupplierCode());
    }
    
        if (e.getImportInvoiceDetailList() != null) {
            List<PurchaseOrderDetailResponse> details = e.getImportInvoiceDetailList().stream().map(d -> {
                PurchaseOrderDetailResponse dr = new PurchaseOrderDetailResponse();
                dr.setId(d.getId());
                dr.setImportInvoiceDetailCode(d.getImportInvoiceDetailCode());
                dr.setQuantity(d.getQuantity());
                dr.setImportPrice(d.getImportPrice());
                dr.setTotalAmount(d.getTotalAmount());
                dr.setQuantitySold(d.getQuantitySold());
                dr.setQuantityCancel(d.getCancelledQuantity());
                // Populate basic product identifiers when product entity exists
                if (d.getProductEntity() != null) {
                    dr.setProductCode(d.getProductEntity().getProductCode());
                    dr.setProductName(d.getProductEntity().getProductName());
                    // set discountValue from product's active promotion if available
                    try {
                        String promoCode = d.getProductEntity().getPromotionCode();
                        if (promoCode != null && !promoCode.isBlank()) {
                            var promo = promotionRepository.findByPromotionCode(promoCode).orElse(null);
                            if (promo != null && isPromotionValid(promo)) {
                                dr.setDiscountValue(promo.getValue());
                            }
                        }
                    } catch (Exception ignored) {}
                }
                // set created date if available
                try {
                    dr.setCreatedDate(d.getCreatedDate());
                } catch (Exception ex) {
                    // ignore if not present
                }
                return dr;
            }).collect(Collectors.toList());
            r.setDetails(details);
        }
        // set created date for order
        try {
            r.setCreatedDate(e.getCreatedDate());
        } catch (Exception ex) {
            // ignore
        }
        // include reason if present (e.g., when order is rejected)
        if (e.getReason() != null) r.setReason(e.getReason());
        return r;
    }

    // Filtered response: only show details that match the given productCode
    private PurchaseOrderResponse toRespFiltered(PurchaseOrderEntity e, String productCode) {
        if (e == null) return null;
        PurchaseOrderResponse r = new PurchaseOrderResponse();
        r.setId(e.getId());
        r.setImportInvoiceCode(e.getImportInvoiceCode());
        r.setDiscount(e.getDiscount());
        r.setStatus(e.getStatus());
        r.setTotalAmount(e.getTotalAmount());
        r.setDescription(e.getDescription());
    if (e.getEmployeeEntity() != null) r.setEmployeeCode(e.getEmployeeEntity().getEmployeeCode());
    if (e.getSupplierEntity() != null) {
        r.setSupplierName(e.getSupplierEntity().getSupplierName());
        r.setSupplierCode(e.getSupplierEntity().getSupplierCode());
    }
    
        if (e.getImportInvoiceDetailList() != null) {
            // Filter details to only include those with the matching productCode
            List<PurchaseOrderDetailResponse> details = e.getImportInvoiceDetailList().stream()
                .filter(d -> d.getProductEntity() != null && d.getProductEntity().getProductCode().equals(productCode))
                .map(d -> {
                    PurchaseOrderDetailResponse dr = new PurchaseOrderDetailResponse();
                    dr.setId(d.getId());
                    dr.setImportInvoiceDetailCode(d.getImportInvoiceDetailCode());
                    dr.setQuantity(d.getQuantity());
                    dr.setImportPrice(d.getImportPrice());
                    dr.setTotalAmount(d.getTotalAmount());
                    dr.setQuantitySold(d.getQuantitySold());
                    dr.setQuantityCancel(d.getCancelledQuantity());
                    dr.setProductCode(d.getProductEntity().getProductCode());
                    dr.setProductName(d.getProductEntity().getProductName());
                    try {
                        String promoCode = d.getProductEntity().getPromotionCode();
                        if (promoCode != null && !promoCode.isBlank()) {
                            var promo = promotionRepository.findByPromotionCode(promoCode).orElse(null);
                            if (promo != null && isPromotionValid(promo)) {
                                dr.setDiscountValue(promo.getValue());
                            }
                        }
                    } catch (Exception ignored) {}
                    // set created date if available
                    try {
                        dr.setCreatedDate(d.getCreatedDate());
                    } catch (Exception ex) {
                        // ignore if not present
                    }
                    return dr;
                }).collect(Collectors.toList());
            r.setDetails(details);
        }
        // set created date for order
        try {
            r.setCreatedDate(e.getCreatedDate());
        } catch (Exception ex) {
            // ignore
        }
        // include reason if present (e.g., when order is rejected)
        if (e.getReason() != null) r.setReason(e.getReason());
        return r;
    }

    private boolean isPromotionValid(com.devsoga.BookStore_V2.enties.PromotionEntity promotion) {
        if (promotion == null || !Boolean.TRUE.equals(promotion.getStatus())) return false;
        java.time.LocalDateTime now = java.time.LocalDateTime.now();
        if (promotion.getStartDate() != null) {
            java.time.LocalDateTime s = promotion.getStartDate().toLocalDateTime();
            if (now.isBefore(s)) return false;
        }
        if (promotion.getEndDate() != null) {
            java.time.LocalDateTime e = promotion.getEndDate().toLocalDateTime();
            if (now.isAfter(e)) return false;
        }
        return true;
    }
}
