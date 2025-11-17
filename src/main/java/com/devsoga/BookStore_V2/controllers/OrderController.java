package com.devsoga.BookStore_V2.controllers;

import com.devsoga.BookStore_V2.dtos.requests.OrderRequest;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("")
    public BaseRespone createOrder(@RequestBody OrderRequest request) {
        return orderService.createOrder(request);
    }

    @GetMapping("")
    public BaseRespone getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{orderCode}")
    public BaseRespone getByCode(@PathVariable String orderCode) {
        return orderService.getByCode(orderCode);
    }

    @PostMapping("/update/{orderCode}")
    public BaseRespone updateOrder(@PathVariable String orderCode, @RequestBody OrderRequest req) {
        return orderService.update(orderCode, req);
    }

    @DeleteMapping("/{orderCode}")
    public BaseRespone deleteOrder(@PathVariable String orderCode) {
        return orderService.softDelete(orderCode);
    }

    // Order detail endpoints
    @GetMapping("/{orderCode}/details")
    public BaseRespone getDetails(@PathVariable String orderCode) {
        return orderService.getDetails(orderCode);
    }

    @PostMapping("/{orderCode}/details/create")
    public BaseRespone createDetail(@PathVariable String orderCode, @RequestBody com.devsoga.BookStore_V2.dtos.requests.OrderDetailRequest req) {
        return orderService.createDetail(orderCode, req);
    }

    @PostMapping("/{orderCode}/details/update/{detailCode}")
    public BaseRespone updateDetail(@PathVariable String orderCode, @PathVariable String detailCode, @RequestBody com.devsoga.BookStore_V2.dtos.requests.OrderDetailRequest req) {
        return orderService.updateDetail(orderCode, detailCode, req);
    }

    @PostMapping("/{orderCode}/details/delete/{detailCode}")
    public BaseRespone deleteDetail(@PathVariable String orderCode, @PathVariable String detailCode) {
        return orderService.deleteDetail(orderCode, detailCode);
    }
}
