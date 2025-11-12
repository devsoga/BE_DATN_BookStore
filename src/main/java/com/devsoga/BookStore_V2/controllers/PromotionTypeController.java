package com.devsoga.BookStore_V2.controllers;

import com.devsoga.BookStore_V2.dtos.requests.PromotionTypeRequest;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.services.PromotionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/promotion-types")
public class PromotionTypeController {

    @Autowired
    private PromotionTypeService promotionTypeService;

    @PostMapping("/create")
    public BaseRespone create(@RequestBody PromotionTypeRequest req) {
        return promotionTypeService.create(req);
    }

    @GetMapping("")
    public BaseRespone getAll() {
        return promotionTypeService.getAll();
    }

    @GetMapping("/{promotionTypeCode}")
    public BaseRespone getByCode(@PathVariable String promotionTypeCode) {
        return promotionTypeService.getByCode(promotionTypeCode);
    }

    @PostMapping("/update/{promotionTypeCode}")
    public BaseRespone update(@PathVariable String promotionTypeCode, @RequestBody PromotionTypeRequest req) {
        return promotionTypeService.update(promotionTypeCode, req);
    }

    @PostMapping("/delete/{promotionTypeCode}")
    public BaseRespone delete(@PathVariable String promotionTypeCode) {
        return promotionTypeService.delete(promotionTypeCode);
    }
}
