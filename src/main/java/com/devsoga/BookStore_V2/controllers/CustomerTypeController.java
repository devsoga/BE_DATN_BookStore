package com.devsoga.BookStore_V2.controllers;

import com.devsoga.BookStore_V2.dtos.requests.CustomerTypeRequest;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.services.CustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/customer-types")
public class CustomerTypeController {

    @Autowired
    private CustomerTypeService customerTypeService;

    @PostMapping("/create")
    public BaseRespone create(@RequestBody CustomerTypeRequest req) {
        return customerTypeService.create(req);
    }

    @GetMapping("")
    public BaseRespone getAll() {
        return customerTypeService.getAll();
    }

    @GetMapping("/{customerTypeCode}")
    public BaseRespone getByCode(@PathVariable String customerTypeCode) {
        return customerTypeService.getByCode(customerTypeCode);
    }

    @PostMapping("/update/{customerTypeCode}")
    public BaseRespone update(@PathVariable String customerTypeCode, @RequestBody CustomerTypeRequest req) {
        return customerTypeService.update(customerTypeCode, req);
    }

    @PostMapping("/delete/{customerTypeCode}")
    public BaseRespone delete(@PathVariable String customerTypeCode) {
        return customerTypeService.delete(customerTypeCode);
    }
}
