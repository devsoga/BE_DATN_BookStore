package com.devsoga.BookStore_V2.controllers;

import com.devsoga.BookStore_V2.dtos.requests.FavoriteRequest;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.services.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @PostMapping("")
    public BaseRespone createFavorite(@RequestBody FavoriteRequest request) {
        return favoriteService.createFavorite(request);
    }

    @GetMapping("/{customerCode}")
    public BaseRespone getFavoritesByCustomerCode(@PathVariable("customerCode") String customerCode) {
        return favoriteService.getFavoritesByCustomerCode(customerCode);
    }

    @GetMapping("delete/{customerCode}/{productCode}")
    public BaseRespone getFavorite(@PathVariable("customerCode") String customerCode,
                                   @PathVariable("productCode") String productCode) {
        return favoriteService.getFavorite(customerCode, productCode);
    }

    @PostMapping("/{customerCode}/{productCode}")
    public BaseRespone deleteFavorite(@PathVariable("customerCode") String customerCode,
                                      @PathVariable("productCode") String productCode) {
        return favoriteService.deleteFavorite(customerCode, productCode);
    }
}
