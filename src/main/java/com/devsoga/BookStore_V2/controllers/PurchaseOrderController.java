package com.devsoga.BookStore_V2.controllers;

import com.devsoga.BookStore_V2.dtos.requests.PurchaseOrderRequest;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.services.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/import-invoices")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @GetMapping("")
    public BaseRespone getAll() {
        return purchaseOrderService.getAll();
    }

    @GetMapping("/{code}")
    public BaseRespone getByCode(@PathVariable String code) {
        return purchaseOrderService.getByCode(code);
    }

    @PostMapping("/create")
    public BaseRespone create(@RequestBody PurchaseOrderRequest req) {
        return purchaseOrderService.create(req);
    }

    @PostMapping("/update/{code}")
    public BaseRespone update(@PathVariable String code, @RequestBody PurchaseOrderRequest req) {
        return purchaseOrderService.update(code, req);
    }

    @GetMapping("/by-product/{productCode}")
    public BaseRespone getByProductCode(@PathVariable String productCode) {
        return purchaseOrderService.getByProductCode(productCode);
    }

    @PostMapping("/delete/{code}")
    public BaseRespone delete(@PathVariable String code) {
        return purchaseOrderService.delete(code);
    }

  
    // Approve by importInvoiceCode (string)
    @PostMapping("/approve/{code}")
    public BaseRespone approveByCode(@PathVariable String code) {
        return purchaseOrderService.approveByCode(code);
    }

  
    @PostMapping("/reject/{code}")
    public BaseRespone rejectByCode(@PathVariable String code, @RequestBody com.devsoga.BookStore_V2.dtos.requests.RejectRequest req) {
        return purchaseOrderService.rejectByCode(code, req.getReason());
    }
}
