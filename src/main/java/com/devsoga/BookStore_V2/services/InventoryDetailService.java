package com.devsoga.BookStore_V2.services;

import com.devsoga.BookStore_V2.dtos.requests.InventoryDetailRequest;
import com.devsoga.BookStore_V2.dtos.responses.InventoryDetailResponse;
import com.devsoga.BookStore_V2.enties.InventoryDetailEntity;
import com.devsoga.BookStore_V2.enties.InventoryEntity;
import com.devsoga.BookStore_V2.enties.PurchaseOrderEntity;
import com.devsoga.BookStore_V2.enties.PurchaseOrderDetailEntity;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.repositories.InventoryDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class InventoryDetailService {

    @Autowired
    private InventoryDetailRepository inventoryDetailRepository;

    @Autowired
    private com.devsoga.BookStore_V2.repositories.InventoryRepository inventoryRepository;

    @Autowired
    private com.devsoga.BookStore_V2.repositories.PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private com.devsoga.BookStore_V2.repositories.PurchaseOrderDetailRepository purchaseOrderDetailRepository;

    public BaseRespone create(InventoryDetailRequest req) {
        BaseRespone resp = new BaseRespone();
        try {
            if (req == null) {
                resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                resp.setMessage("Request is required");
                resp.setData(null);
                return resp;
            }

            InventoryDetailEntity e = new InventoryDetailEntity();
            if (req.getInventoryDetailCode() != null) e.setInventoryDetailCode(req.getInventoryDetailCode());
            e.setQuantity(req.getQuantity() == null ? 0 : req.getQuantity());

            // set inventory reference
            if (req.getInventoryCode() != null) {
                var invOpt = inventoryRepository.findByInventoryCode(req.getInventoryCode());
                if (invOpt.isEmpty()) {
                    resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    resp.setMessage("Inventory not found: " + req.getInventoryCode());
                    resp.setData(null);
                    return resp;
                }
                e.setInventoryEntity(invOpt.get());
            }

            // set import invoice detail reference
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
            } else if (req.getImportInvoiceCode() != null) {
                var poOpt = purchaseOrderRepository.findByImportInvoiceCode(req.getImportInvoiceCode());
                if (poOpt.isEmpty()) {
                    resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                    resp.setMessage("Import invoice not found: " + req.getImportInvoiceCode());
                    resp.setData(null);
                    return resp;
                }
                e.setImportInvoiceEntity(poOpt.get());
            }

            InventoryDetailEntity saved = inventoryDetailRepository.save(e);
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

    public BaseRespone getByInventoryCode(String inventoryCode) {
        BaseRespone resp = new BaseRespone();
        try {
            if (inventoryCode == null) {
                resp.setStatusCode(HttpStatus.BAD_REQUEST.value());
                resp.setMessage("inventoryCode is required");
                resp.setData(null);
                return resp;
            }
            var list = inventoryDetailRepository.findByInventoryEntity_InventoryCode(inventoryCode);
            java.util.List<InventoryDetailResponse> data = list.stream().map(this::toResp).collect(java.util.stream.Collectors.toList());
            resp.setStatusCode(HttpStatus.OK.value());
            resp.setMessage("Success");
            resp.setData(data);
        } catch (Exception ex) {
            resp.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            resp.setMessage("Failed: " + ex.getMessage());
            resp.setData(null);
        }
        return resp;
    }

    private InventoryDetailResponse toResp(InventoryDetailEntity e) {
        if (e == null) return null;
        InventoryDetailResponse r = new InventoryDetailResponse();
        r.setId(e.getId());
        r.setInventoryDetailCode(e.getInventoryDetailCode());
        try { r.setInventoryCode(e.getInventoryEntity() != null ? e.getInventoryEntity().getInventoryCode() : null);} catch (Exception ignore) {}
        try { r.setImportInvoiceCode(e.getImportInvoiceEntity() != null ? e.getImportInvoiceEntity().getImportInvoiceCode() : null);} catch (Exception ignore) {}
        try { r.setImportInvoiceDetailCode(e.getImportInvoiceDetailEntity() != null ? e.getImportInvoiceDetailEntity().getImportInvoiceDetailCode() : null);} catch (Exception ignore) {}
        r.setQuantity(e.getQuantity());
      
        return r;
    }
}
