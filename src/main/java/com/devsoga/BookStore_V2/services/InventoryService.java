package com.devsoga.BookStore_V2.services;

import com.devsoga.BookStore_V2.dtos.requests.InventoryCreateRequest;
import com.devsoga.BookStore_V2.dtos.requests.InventoryRequest;
import com.devsoga.BookStore_V2.dtos.responses.InventoryResponse;
import com.devsoga.BookStore_V2.enties.InventoryEntity;
import com.devsoga.BookStore_V2.enties.ProductEntity;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.repositories.InventoryRepository;
import com.devsoga.BookStore_V2.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private com.devsoga.BookStore_V2.services.PurchaseOrderService purchaseOrderService;

    public BaseRespone getAllInventories() {
        BaseRespone resp = new BaseRespone();
        try {
            List<InventoryEntity> list = inventoryRepository.findAll().stream()
                    .filter(i -> Boolean.TRUE.equals(i.getStatus()))
                    .collect(Collectors.toList());
            List<InventoryResponse> data = list.stream().map(this::toResp).collect(Collectors.toList());
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(data);
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to fetch inventories: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone getByInventoryCode(String inventoryCode) {
        BaseRespone resp = new BaseRespone();
        try {
            InventoryEntity ent = inventoryRepository.findByInventoryCode(inventoryCode).orElse(null);
            if (ent == null || !Boolean.TRUE.equals(ent.getStatus())) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Inventory not found");
                resp.setData(null);
            } else {
                resp.setStatusCode(HttpStatus.OK.value());
                resp.setMessage("Success");
                resp.setData(toResp(ent));
            }
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to fetch inventory: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone getByProductCode(String productCode) {
        BaseRespone resp = new BaseRespone();
        try {
            if (productCode == null || productCode.isBlank()) {
                resp.setStatusCode(org.springframework.http.HttpStatus.BAD_REQUEST.value());
                resp.setMessage("productCode is required");
                resp.setData(null);
                return resp;
            }

            java.util.List<InventoryEntity> list = inventoryRepository.findByProductEntity_ProductCodeAndStatus(productCode, Boolean.TRUE);
            if (list == null || list.isEmpty()) {
                resp.setStatusCode(org.springframework.http.HttpStatus.NOT_FOUND.value());
                resp.setMessage("No inventory records found for product: " + productCode);
                resp.setData(java.util.Collections.emptyList());
                return resp;
            }

            java.util.List<InventoryResponse> data = list.stream().map(this::toResp).collect(java.util.stream.Collectors.toList());
            resp.setStatusCode(org.springframework.http.HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(data);
        } catch (Exception e) {
            resp.setStatusCode(org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to fetch inventories by product: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone createInventory(InventoryCreateRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            if (req == null || req.getInventoryCode() == null || req.getInventoryCode().isBlank()) {
                resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                resp.setMessage("inventoryCode is required");
                resp.setData(null);
                return resp;
            }

            if (inventoryRepository.findByInventoryCode(req.getInventoryCode()).isPresent()) {
                resp.setStatusCode(HttpStatus.CONFLICT.value());
                resp.setMessage("Inventory already exists");
                resp.setData(null);
                return resp;
            }

            InventoryEntity ent = new InventoryEntity();
            ent.setInventoryCode(req.getInventoryCode());
            ent.setStatus(req.getStatus() == null ? Boolean.TRUE : req.getStatus());

            if (req.getProductCode() != null && !req.getProductCode().isBlank()) {
                ProductEntity p = productRepository.findByProductCode(req.getProductCode()).orElse(null);
                ent.setProductEntity(p);
            }

            // importInvoiceDetailEntity not set here (no repository available)

            InventoryEntity saved = inventoryRepository.save(ent);
            resp.setStatusCode(HttpStatus.CREATED.value());
            resp.setMessage("Created");
            resp.setData(toResp(saved));
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to create inventory: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone updateInventory(String inventoryCode, InventoryRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            InventoryEntity ent = inventoryRepository.findByInventoryCode(inventoryCode).orElse(null);
            if (ent == null) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Inventory not found");
                resp.setData(null);
                return resp;
            }

            if (req.getStatus() != null) ent.setStatus(req.getStatus());

            if (req.getProductCode() != null) {
                ProductEntity p = productRepository.findByProductCode(req.getProductCode()).orElse(null);
                ent.setProductEntity(p);
            }

            // importInvoiceDetailCode ignored for now

            InventoryEntity saved = inventoryRepository.save(ent);
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Updated");
            resp.setData(toResp(saved));
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to update inventory: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    public BaseRespone deleteInventory(String inventoryCode) {
        BaseRespone resp = new BaseRespone();
        try {
            InventoryEntity ent = inventoryRepository.findByInventoryCode(inventoryCode).orElse(null);
            if (ent == null) {
                resp.setStatusCode(HttpStatus.NOT_FOUND.value());
                resp.setMessage("Inventory not found");
                resp.setData(null);
                return resp;
            }
            // soft delete
            ent.setStatus(Boolean.FALSE);
            inventoryRepository.save(ent);
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Deleted (soft)");
            resp.setData(null);
        } catch (Exception e) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed to delete inventory: " + e.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    private InventoryResponse toResp(InventoryEntity e) {
        if (e == null) return null;
        InventoryResponse r = new InventoryResponse();
    r.setInventoryCode(e.getInventoryCode());
    r.setStatus(e.getStatus());
        try {
            if (e.getProductEntity() != null) {
                com.devsoga.BookStore_V2.dtos.responses.InventoryProductDto p = new com.devsoga.BookStore_V2.dtos.responses.InventoryProductDto();
                p.setProductCode(e.getProductEntity().getProductCode());
                p.setProductName(e.getProductEntity().getProductName());
                r.setProduct(p);

                // compute quantityOnHand by summing inventory_detail.quantity for the product
                try {
                    String prodCode = e.getProductEntity().getProductCode();
                    Integer total = inventoryRepository.findTotalQuantityByProductCode(prodCode);
                    r.setQuantityOnHand(total == null ? 0 : total);
                } catch (Exception ignoreQty) {
                    r.setQuantityOnHand(0);
                }
            }
        } catch (Exception ignored) {}
        r.setCreatedDate(e.getCreatedDate());
        r.setUpdatedDate(e.getUpdatedDate());
        return r;
    }
}
