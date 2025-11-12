package com.devsoga.BookStore_V2.controllers;

import com.devsoga.BookStore_V2.dtos.requests.CancelledProductRequest;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.services.CancelledProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/cancelled-products")
public class CancelledProductController {

    @Autowired
    private CancelledProductService cancelledProductService;

    @GetMapping()
    public ResponseEntity<BaseRespone> getAll() {
        BaseRespone response = cancelledProductService.getAll();
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/by-import-invoice/{importInvoiceCode}")
    public ResponseEntity<BaseRespone> getByImportInvoiceCode(@PathVariable String importInvoiceCode) {
        BaseRespone response = cancelledProductService.getByImportInvoiceCode(importInvoiceCode);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/by-product/{productCode}")
    public ResponseEntity<BaseRespone> getByProductCode(@PathVariable String productCode) {
        BaseRespone response = cancelledProductService.getByProductCode(productCode);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/by-import-invoice-detail/{importInvoiceDetailCode}")
    public ResponseEntity<BaseRespone> getByImportInvoiceDetailCode(@PathVariable String importInvoiceDetailCode) {
        BaseRespone response = cancelledProductService.getByImportInvoiceDetailCode(importInvoiceDetailCode);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<BaseRespone> create(@RequestBody CancelledProductRequest request) {
        BaseRespone response = cancelledProductService.create(request);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}
