package com.devsoga.BookStore_V2.controllers;

import com.devsoga.BookStore_V2.dtos.requests.CouponRequest;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.services.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/coupons")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/create")
    public BaseRespone create(@RequestBody CouponRequest req) {
        return couponService.create(req);
    }

    @GetMapping("")
    public BaseRespone getAll() {
        return couponService.getAll();
    }

    @GetMapping("/{couponCode}")
    public BaseRespone getByCode(@PathVariable String couponCode) {
        return couponService.getByCode(couponCode);
    }

    @PostMapping("/update/{couponCode}")
    public BaseRespone update(@PathVariable String couponCode, @RequestBody CouponRequest req) {
        return couponService.update(couponCode, req);
    }

    @PostMapping("/delete/{couponCode}")
    public BaseRespone delete(@PathVariable String couponCode) {
        return couponService.delete(couponCode);
    }
}
