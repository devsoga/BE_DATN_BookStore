package com.devsoga.BookStore_V2.controllers;

import com.devsoga.BookStore_V2.dtos.requests.PromotionRequest;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/promotions")
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @PostMapping("/create")
    public BaseRespone create(@RequestBody PromotionRequest req) {
        return promotionService.create(req);
    }

    @GetMapping("")
    public BaseRespone getAll() {
        return promotionService.getAll();
    }

    @GetMapping("/{promotionCode}")
    public BaseRespone getByCode(@PathVariable String promotionCode) {
        return promotionService.getByCode(promotionCode);
    }

    @PostMapping("/update/{promotionCode}")
    public BaseRespone update(@PathVariable String promotionCode, @RequestBody PromotionRequest req) {
        return promotionService.update(promotionCode, req);
    }

    @PostMapping("/delete/{promotionCode}")
    public BaseRespone delete(@PathVariable String promotionCode) {
        return promotionService.delete(promotionCode);
    }
}
