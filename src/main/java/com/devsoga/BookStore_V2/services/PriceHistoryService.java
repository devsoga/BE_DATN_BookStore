package com.devsoga.BookStore_V2.services;

import com.devsoga.BookStore_V2.dtos.responses.PriceHistoryResponse;
import com.devsoga.BookStore_V2.dtos.responses.ProductRespone;
import com.devsoga.BookStore_V2.enties.PriceHistoryEntity;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.repositories.PriceHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PriceHistoryService {

    @Autowired
    private PriceHistoryRepository priceHistoryRepository;
    @Autowired
    private com.devsoga.BookStore_V2.repositories.ProductRepository productRepository;

    @Autowired
    private com.devsoga.BookStore_V2.repositories.PurchaseOrderDetailRepository purchaseOrderDetailRepository;
    @Autowired
    private com.devsoga.BookStore_V2.repositories.PurchaseOrderRepository purchaseOrderRepository;

    public BaseRespone getAll() {
        BaseRespone resp = new BaseRespone();
        try {
        List<PriceHistoryEntity> list = priceHistoryRepository.findAll().stream()
            .filter(ph -> ph.getStatus() != null && ph.getStatus())
            .collect(Collectors.toList());
        List<PriceHistoryResponse> data = list.stream().map(this::toResp).collect(Collectors.toList());
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

    public BaseRespone getByImportInvoiceCode(String importInvoiceCode) {
        BaseRespone resp = new BaseRespone();
        try {
        List<PriceHistoryEntity> list = priceHistoryRepository.findByImportInvoiceCode(importInvoiceCode).stream()
            .filter(ph -> ph.getStatus() != null && ph.getStatus())
            .collect(Collectors.toList());
        List<PriceHistoryResponse> data = list.stream().map(this::toResp).collect(Collectors.toList());
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

    public BaseRespone getByImportInvoiceAndProduct(String importInvoiceCode, String productCode) {
        BaseRespone resp = new BaseRespone();
        try {
            // include price history rows regardless of status (including status = false)
            List<PriceHistoryEntity> list = priceHistoryRepository.findByImportInvoiceCode(importInvoiceCode).stream()
                    .filter(ph -> {
                        try {
                            return ph.getProductEntity() != null && productCode != null && productCode.equals(ph.getProductEntity().getProductCode());
                        } catch (Exception ex) {
                            return false;
                        }
                    })
                    .collect(Collectors.toList());
            List<PriceHistoryResponse> data = list.stream().map(this::toResp).collect(Collectors.toList());
            // If no price history exists for this import invoice + product, return the import invoice detail info
            if ((data == null || data.isEmpty()) && productCode != null) {
                var detailOpt = purchaseOrderDetailRepository.findByImportInvoiceEntity_ImportInvoiceCodeAndProductEntity_ProductCode(importInvoiceCode, productCode);
                if (detailOpt.isPresent()) {
                    var d = detailOpt.get();
                    PriceHistoryResponse ph = new PriceHistoryResponse();
                    try { ph.setImportPrice(d.getImportPrice()); } catch (Exception ignore) {}
                    try { ph.setProductCode(d.getProductEntity() != null ? d.getProductEntity().getProductCode() : null); } catch (Exception ignore) {}
                    try { ph.setImportInvoiceDetailCode(d.getImportInvoiceDetailCode()); } catch (Exception ignore) {}
                    data = List.of(ph);
                }
            }
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

    public BaseRespone getByProductCode(String productCode) {
        BaseRespone resp = new BaseRespone();
        try {
            List<PriceHistoryEntity> list = priceHistoryRepository.findActiveByProductCode(productCode);
            List<PriceHistoryResponse> data = list.stream().map(this::toResp).collect(Collectors.toList());
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

    public BaseRespone create(com.devsoga.BookStore_V2.dtos.requests.PriceHistoryRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            if (req == null) {
                resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                resp.setMessage("Request is required");
                resp.setData(null);
                return resp;
            }

            com.devsoga.BookStore_V2.enties.PriceHistoryEntity e = new com.devsoga.BookStore_V2.enties.PriceHistoryEntity();
            if (req.getPriceHistoryCode() != null) e.setPriceHistoryCode(req.getPriceHistoryCode());
            if (req.getUnitPrice() != null) e.setUnitPrice(req.getUnitPrice());
            e.setStatus(req.getStatus() == null ? Boolean.TRUE : req.getStatus());

            // set product entity
            if (req.getProductCode() != null) {
                var pOpt = productRepository.findByProductCode(req.getProductCode()).orElse(null);
                if (pOpt == null) {
                    resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    resp.setMessage("Product not found: " + req.getProductCode());
                    resp.setData(null);
                    return resp;
                }
                e.setProductEntity(pOpt);
            }

            // set import invoice detail entity
            if (req.getImportInvoiceDetailCode() != null) {
                var dOpt = purchaseOrderDetailRepository.findByImportInvoiceDetailCode(req.getImportInvoiceDetailCode());
                if (dOpt.isEmpty()) {
                    resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    resp.setMessage("Import invoice detail not found: " + req.getImportInvoiceDetailCode());
                    resp.setData(null);
                    return resp;
                }
                e.setImportInvoiceDetailEntity(dOpt.get());
                try { e.setImportInvoiceEntity(dOpt.get().getImportInvoiceEntity()); } catch (Exception ignore) {}
            } else if (req.getImportInvoiceCode() != null && req.getProductCode() != null) {
                // try to resolve detail by import invoice code + product code
                var dOpt = purchaseOrderDetailRepository.findByImportInvoiceEntity_ImportInvoiceCodeAndProductEntity_ProductCode(req.getImportInvoiceCode(), req.getProductCode());
                if (dOpt.isEmpty()) {
                    // if import invoice detail not found, still try to set import invoice reference only
                    var poOpt = purchaseOrderRepository.findByImportInvoiceCode(req.getImportInvoiceCode());
                    if (poOpt.isPresent()) {
                        e.setImportInvoiceEntity(poOpt.get());
                    } else {
                        resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                        resp.setMessage("Import invoice detail not found for importInvoiceCode=" + req.getImportInvoiceCode() + " and productCode=" + req.getProductCode());
                        resp.setData(null);
                        return resp;
                    }
                }
                e.setImportInvoiceDetailEntity(dOpt.get());
            }

            PriceHistoryEntity saved = priceHistoryRepository.save(e);
            // After updating this price history, set status = false for all other price_history rows with the same productCode
            try {
                String productCodeToUse = null;
                if (req.getProductCode() != null) productCodeToUse = req.getProductCode();
                else if (saved.getProductEntity() != null) {
                    try { productCodeToUse = saved.getProductEntity().getProductCode(); } catch (Exception ignore) {}
                }
                if (productCodeToUse != null) {
                    List<PriceHistoryEntity> allForProduct = priceHistoryRepository.findByProductEntity_ProductCode(productCodeToUse);
                    List<PriceHistoryEntity> toSave = allForProduct.stream()
                            .filter(ph -> ph.getPriceHistoryCode() != null && !ph.getPriceHistoryCode().equals(saved.getPriceHistoryCode()))
                            .filter(ph -> ph.getStatus() == null || ph.getStatus())
                            .peek(ph -> ph.setStatus(Boolean.FALSE))
                            .collect(Collectors.toList());
                    if (!toSave.isEmpty()) priceHistoryRepository.saveAll(toSave);
                }
            } catch (Exception ignore) {}
            resp.setStatusCode(HttpStatus.CREATED.value());
            resp.setMessage("Created");
            resp.setData(toResp(saved));
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone update(String priceHistoryCode, com.devsoga.BookStore_V2.dtos.requests.PriceHistoryRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = priceHistoryRepository.findByPriceHistoryCode(priceHistoryCode);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Not found");
                resp.setData(null);
                return resp;
            }
            PriceHistoryEntity e = opt.get();
            if (req.getUnitPrice() != null) e.setUnitPrice(req.getUnitPrice());
            if (req.getStatus() != null) e.setStatus(req.getStatus());
            if (req.getProductCode() != null) {
                var pOpt = productRepository.findByProductCode(req.getProductCode()).orElse(null);
                if (pOpt == null) {
                    resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    resp.setMessage("Product not found: " + req.getProductCode());
                    resp.setData(null);
                    return resp;
                }
                e.setProductEntity(pOpt);
            }
            if (req.getImportInvoiceDetailCode() != null) {
                var dOpt = purchaseOrderDetailRepository.findByImportInvoiceDetailCode(req.getImportInvoiceDetailCode());
                if (dOpt.isEmpty()) {
                    resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    resp.setMessage("Import invoice detail not found: " + req.getImportInvoiceDetailCode());
                    resp.setData(null);
                    return resp;
                }
                e.setImportInvoiceDetailEntity(dOpt.get());
            } else if (req.getImportInvoiceCode() != null && req.getProductCode() != null) {
                var dOpt = purchaseOrderDetailRepository.findByImportInvoiceEntity_ImportInvoiceCodeAndProductEntity_ProductCode(req.getImportInvoiceCode(), req.getProductCode());
                if (dOpt.isEmpty()) {
                    var poOpt = purchaseOrderRepository.findByImportInvoiceCode(req.getImportInvoiceCode());
                    if (poOpt.isPresent()) {
                        e.setImportInvoiceEntity(poOpt.get());
                    } else {
                        resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                        resp.setMessage("Import invoice detail not found for importInvoiceCode=" + req.getImportInvoiceCode() + " and productCode=" + req.getProductCode());
                        resp.setData(null);
                        return resp;
                    }
                } else {
                    e.setImportInvoiceDetailEntity(dOpt.get());
                    try { e.setImportInvoiceEntity(dOpt.get().getImportInvoiceEntity()); } catch (Exception ignore) {}
                }
            }

            PriceHistoryEntity saved = priceHistoryRepository.save(e);
            
            // Only deactivate others if this record is being set to status = true
            if (saved.getStatus() != null && saved.getStatus() == Boolean.TRUE) {
                try {
                    String productCodeToUse = null;
                    if (req.getProductCode() != null) productCodeToUse = req.getProductCode();
                    else if (saved.getProductEntity() != null) {
                        try { productCodeToUse = saved.getProductEntity().getProductCode(); } catch (Exception ignore) {}
                    }
                    if (productCodeToUse != null) {
                        List<PriceHistoryEntity> allForProduct = priceHistoryRepository.findByProductEntity_ProductCode(productCodeToUse);
                        List<PriceHistoryEntity> toSave = allForProduct.stream()
                                .filter(ph -> ph.getPriceHistoryCode() != null && !ph.getPriceHistoryCode().equals(saved.getPriceHistoryCode()))
                                .filter(ph -> ph.getStatus() == null || ph.getStatus())
                                .peek(ph -> ph.setStatus(Boolean.FALSE))
                                .collect(Collectors.toList());
                        if (!toSave.isEmpty()) priceHistoryRepository.saveAll(toSave);
                    }
                } catch (Exception ignore) {}
            }
            
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Updated");
            resp.setData(toResp(saved));
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone delete(String priceHistoryCode) {
        BaseRespone resp = new BaseRespone();
        try {
            var opt = priceHistoryRepository.findByPriceHistoryCode(priceHistoryCode);
            if (opt.isEmpty()) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Not found");
                resp.setData(null);
                return resp;
            }
            PriceHistoryEntity e = opt.get();
            // soft delete
            e.setStatus(Boolean.FALSE);
            priceHistoryRepository.save(e);
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

    private PriceHistoryResponse toResp(PriceHistoryEntity e) {
        if (e == null) return null;
        PriceHistoryResponse r = new PriceHistoryResponse();
        r.setId(e.getId());
        r.setPriceHistoryCode(e.getPriceHistoryCode());
        r.setUnitPrice(e.getUnitPrice());
        r.setStatus(e.getStatus());
        
        // Set productCode only (do not include full product object)
        try {
            if (e.getProductEntity() != null) {
                r.setProductCode(e.getProductEntity().getProductCode());
            }
        } catch (Exception ex) {}
        
        try { 
            if (e.getImportInvoiceDetailEntity() != null) {
                r.setImportInvoiceDetailCode(e.getImportInvoiceDetailEntity().getImportInvoiceDetailCode());
                // include the import_price from the linked import_invoice_detail
                try { r.setImportPrice(e.getImportInvoiceDetailEntity().getImportPrice()); } catch (Exception ignore) {}
            } 
        } catch (Exception ex) {}
        
        try { r.setCreatedDate(e.getCreatedDate()); } catch (Exception ex) {}
        return r;
    }
}
