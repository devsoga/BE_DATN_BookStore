package com.devsoga.BookStore_V2.services;

import com.devsoga.BookStore_V2.dtos.requests.CancelledProductRequest;
import com.devsoga.BookStore_V2.dtos.responses.CancelledProductResponse;
import com.devsoga.BookStore_V2.enties.CancelledProductEntity;
import com.devsoga.BookStore_V2.enties.ProductEntity;
import com.devsoga.BookStore_V2.enties.PurchaseOrderDetailEntity;
import com.devsoga.BookStore_V2.enties.PurchaseOrderEntity;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.repositories.CancelledProductRepository;
import com.devsoga.BookStore_V2.repositories.ProductRepository;
import com.devsoga.BookStore_V2.repositories.PurchaseOrderDetailRepository;
import com.devsoga.BookStore_V2.repositories.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CancelledProductService {

    @Autowired
    private CancelledProductRepository cancelledProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private PurchaseOrderDetailRepository purchaseOrderDetailRepository;

    @Autowired
    private com.devsoga.BookStore_V2.services.PurchaseOrderService purchaseOrderService;

    public BaseRespone getAll() {
        try {
            List<CancelledProductEntity> list = cancelledProductRepository.findAll();
            List<CancelledProductResponse> responses = list.stream()
                    .map(this::toResp)
                    .collect(Collectors.toList());
            BaseRespone resp = new BaseRespone();
            resp.setStatusCode(200);
            resp.setMessage("Get all cancelled products successfully");
            resp.setData(responses);
            return resp;
        } catch (Exception e) {
            BaseRespone resp = new BaseRespone();
            resp.setStatusCode(500);
            resp.setMessage("Error retrieving cancelled products: " + e.getMessage());
            resp.setData(null);
            return resp;
        }
    }

    public BaseRespone getByImportInvoiceCode(String importInvoiceCode) {
        try {
            List<CancelledProductEntity> list = cancelledProductRepository.findByImportInvoiceEntity_ImportInvoiceCode(importInvoiceCode);
            List<CancelledProductResponse> responses = list.stream()
                    .map(this::toResp)
                    .collect(Collectors.toList());
            BaseRespone resp = new BaseRespone();
            resp.setStatusCode(200);
            resp.setMessage("Get cancelled products by import invoice code successfully");
            resp.setData(responses);
            return resp;
        } catch (Exception e) {
            BaseRespone resp = new BaseRespone();
            resp.setStatusCode(500);
            resp.setMessage("Error retrieving cancelled products: " + e.getMessage());
            resp.setData(null);
            return resp;
        }
    }

    public BaseRespone getByProductCode(String productCode) {
        try {
            List<CancelledProductEntity> list = cancelledProductRepository.findByProductEntity_ProductCode(productCode);
            List<CancelledProductResponse> responses = list.stream()
                    .map(this::toResp)
                    .collect(Collectors.toList());
            BaseRespone resp = new BaseRespone();
            resp.setStatusCode(200);
            resp.setMessage("Get cancelled products by product code successfully");
            resp.setData(responses);
            return resp;
        } catch (Exception e) {
            BaseRespone resp = new BaseRespone();
            resp.setStatusCode(500);
            resp.setMessage("Error retrieving cancelled products: " + e.getMessage());
            resp.setData(null);
            return resp;
        }
    }

    public BaseRespone getByImportInvoiceDetailCode(String importInvoiceDetailCode) {
        try {
            List<CancelledProductEntity> list = cancelledProductRepository.findByImportInvoiceDetailEntity_ImportInvoiceDetailCode(importInvoiceDetailCode);
            List<CancelledProductResponse> responses = list.stream()
                    .map(this::toResp)
                    .collect(Collectors.toList());
            BaseRespone resp = new BaseRespone();
            resp.setStatusCode(200);
            resp.setMessage("Get cancelled products by import invoice detail code successfully");
            resp.setData(responses);
            return resp;
        } catch (Exception e) {
            BaseRespone resp = new BaseRespone();
            resp.setStatusCode(500);
            resp.setMessage("Error retrieving cancelled products: " + e.getMessage());
            resp.setData(null);
            return resp;
        }
    }

    public BaseRespone create(CancelledProductRequest request) {
        try {
            // Validate product exists
            ProductEntity product = productRepository.findByProductCode(request.getProductCode())
                    .orElseThrow(() -> new RuntimeException("Product not found: " + request.getProductCode()));

            // Validate import invoice exists
            PurchaseOrderEntity importInvoice = purchaseOrderRepository.findByImportInvoiceCode(request.getImportInvoiceCode())
                    .orElseThrow(() -> new RuntimeException("Import invoice not found: " + request.getImportInvoiceCode()));

            // Validate import invoice detail exists
            PurchaseOrderDetailEntity importInvoiceDetail = purchaseOrderDetailRepository.findByImportInvoiceDetailCode(request.getImportInvoiceDetailCode())
                    .orElseThrow(() -> new RuntimeException("Import invoice detail not found: " + request.getImportInvoiceDetailCode()));

            // Create cancelled product entity
            CancelledProductEntity entity = new CancelledProductEntity();
            entity.setCancelledProductCode(request.getCancelledProductCode());
            entity.setQuantity(request.getQuantity());
            entity.setReason(request.getReason());
            entity.setImportInvoiceEntity(importInvoice);
            entity.setProductEntity(product);
            entity.setImportInvoiceDetailEntity(importInvoiceDetail);

            CancelledProductEntity saved = cancelledProductRepository.save(entity);
            // After saving cancelled record, update cancelled_quantity in import_invoice_detail and update inventory_detail quantities
            try {
                if (importInvoiceDetail != null) {
                    // Update cancelled_quantity in import_invoice_detail to be sum of all cancelled_product for this detail
                    Long totalCancelled = cancelledProductRepository.sumQuantityByImportInvoiceDetailCode(importInvoiceDetail.getImportInvoiceDetailCode());
                    importInvoiceDetail.setCancelledQuantity(totalCancelled == null ? 0 : totalCancelled.intValue());
                    purchaseOrderDetailRepository.save(importInvoiceDetail);
                    
                    // Update inventory_detail quantities for the affected import invoice detail
                    purchaseOrderService.updateInventoryDetailQuantities(importInvoiceDetail);
                }
            } catch (Exception ignore) {}
            CancelledProductResponse response = toResp(saved);
            
            BaseRespone resp = new BaseRespone();
            resp.setStatusCode(201);
            resp.setMessage("Cancelled product created successfully");
            resp.setData(response);
            return resp;
        } catch (RuntimeException e) {
            BaseRespone resp = new BaseRespone();
            resp.setStatusCode(404);
            resp.setMessage(e.getMessage());
            resp.setData(null);
            return resp;
        } catch (Exception e) {
            BaseRespone resp = new BaseRespone();
            resp.setStatusCode(500);
            resp.setMessage("Error creating cancelled product: " + e.getMessage());
            resp.setData(null);
            return resp;
        }
    }

    private CancelledProductResponse toResp(CancelledProductEntity entity) {
        CancelledProductResponse resp = new CancelledProductResponse();
        resp.setId(entity.getId());
        resp.setCancelledProductCode(entity.getCancelledProductCode());
        resp.setQuantity(entity.getQuantity());
        resp.setReason(entity.getReason());
        resp.setImportInvoiceCode(entity.getImportInvoiceEntity() != null ? entity.getImportInvoiceEntity().getImportInvoiceCode() : null);
        resp.setProductCode(entity.getProductEntity() != null ? entity.getProductEntity().getProductCode() : null);
        resp.setImportInvoiceDetailCode(entity.getImportInvoiceDetailEntity() != null ? entity.getImportInvoiceDetailEntity().getImportInvoiceDetailCode() : null);
        resp.setCreatedDate(entity.getCreatedDate());
        resp.setUpdatedDate(entity.getUpdatedDate());
        return resp;
    }
}
