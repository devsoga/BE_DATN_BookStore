package com.devsoga.BookStore_V2.controllers;

import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.services.PriceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/price-histories")
public class PriceHistoryController {

    @Autowired
    private PriceHistoryService priceHistoryService;

    @GetMapping("")
    public BaseRespone getAll() {
        return priceHistoryService.getAll();
    }

    @GetMapping("/by-import-invoice/{code}/{productCode}")
    public BaseRespone getByImportInvoiceAndProduct(@PathVariable String code, @PathVariable String productCode) {
        return priceHistoryService.getByImportInvoiceAndProduct(code, productCode);
    }

    @GetMapping("/by-product/{productCode}")
    public BaseRespone getByProductCode(@PathVariable String productCode) {
        return priceHistoryService.getByProductCode(productCode);
    }

    @PostMapping("/create")
    public BaseRespone create(@RequestBody com.devsoga.BookStore_V2.dtos.requests.PriceHistoryRequest req) {
        return priceHistoryService.create(req);
    }

    @PostMapping("/update/{priceHistoryCode}")
    public BaseRespone update(@PathVariable String priceHistoryCode, @RequestBody com.devsoga.BookStore_V2.dtos.requests.PriceHistoryRequest req) {
        return priceHistoryService.update(priceHistoryCode, req);
    }

    @DeleteMapping("/{priceHistoryCode}")
    public BaseRespone delete(@PathVariable String priceHistoryCode) {
        return priceHistoryService.delete(priceHistoryCode);
    }
}
