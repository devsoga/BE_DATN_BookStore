package com.devsoga.BookStore_V2.controllers;

import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.services.SupplierProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/supplier-products")
public class SupplierProductController {

    @Autowired
    private SupplierProductService supplierProductService;

    @GetMapping("")
    public BaseRespone getAll() {
        return supplierProductService.getAll();
    }

    @GetMapping("/{code}")
    public BaseRespone getByCode(@PathVariable String code) {
        return supplierProductService.getByCode(code);
    }

    @PostMapping("/create")
    public BaseRespone create(@RequestBody com.devsoga.BookStore_V2.dtos.requests.SupplierProductRequest req) {
        return supplierProductService.create(req);
    }

    @PostMapping("/update/{code}")
    public BaseRespone update(@PathVariable String code, @RequestBody com.devsoga.BookStore_V2.dtos.requests.SupplierProductRequest req) {
        return supplierProductService.update(code, req);
    }

    @DeleteMapping("/{code}")
    public BaseRespone delete(@PathVariable String code, @RequestParam(required = false, defaultValue = "false") boolean hard) {
        return supplierProductService.delete(code, hard);
    }
}
