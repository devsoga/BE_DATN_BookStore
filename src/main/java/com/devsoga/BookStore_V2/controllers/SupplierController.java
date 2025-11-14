package com.devsoga.BookStore_V2.controllers;

import com.devsoga.BookStore_V2.dtos.requests.SupplierRequest;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public BaseRespone getAll() {
        return supplierService.getAll();
    }

    @GetMapping("/{supplierCode}")
    public BaseRespone getById(@PathVariable String supplierCode) {
        return supplierService.getById(supplierCode);
    }

    @GetMapping("/{supplierCode}/products")
    public BaseRespone getProductsBySupplier(@PathVariable String supplierCode) {
        return supplierService.getProductsBySupplier(supplierCode);
    }

    @PostMapping("/create")
    public BaseRespone create(@RequestBody SupplierRequest req) {
        return supplierService.create(req);
    }

    @PostMapping("/update/{supplierCode}")
    public BaseRespone update(@PathVariable String supplierCode, @RequestBody SupplierRequest req) {
        return supplierService.update(supplierCode, req);
    }

    @PostMapping("/delete/{supplierCode}")
    public BaseRespone delete(@PathVariable String supplierCode) {
        return supplierService.delete(supplierCode);
    }
}
