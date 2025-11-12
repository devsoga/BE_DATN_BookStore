package com.devsoga.BookStore_V2.controllers;

import com.devsoga.BookStore_V2.dtos.requests.CartRequest;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("")
    public BaseRespone createCart(@RequestBody CartRequest request) {
        return cartService.createCart(request);
    }

    @GetMapping("/{customerCode}")
    public BaseRespone getCartByCustomerCode(@PathVariable("customerCode") String customerCode) {
        return cartService.getCartByCustomerCode(customerCode);
    }

    @PostMapping("/{customerCode}/{productCode}")
    public BaseRespone deleteCartItem(@PathVariable("customerCode") String customerCode,
                                      @PathVariable("productCode") String productCode) {
        return cartService.deleteCartItem(customerCode, productCode);
    }

    // POST /v1/api/cart/update-quantity - update quantity for a cart item (customerCode, productCode, quantity in body)
    @PostMapping("/update-quantity")
    public BaseRespone updateCartQuantity(@RequestBody CartRequest request) {
        return cartService.updateCartQuantity(request);
    }

    // DELETE /v1/api/cart/customer/{customerCode} - delete all cart items for a customer
    @PostMapping("/delete-all/{customerCode}")
    public BaseRespone deleteAllCartByCustomer(@PathVariable("customerCode") String customerCode) {
        return cartService.deleteAllByCustomerCode(customerCode);
    }
}
