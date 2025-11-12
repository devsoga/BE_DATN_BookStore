package com.devsoga.BookStore_V2.controllers;

import com.devsoga.BookStore_V2.dtos.requests.CategoryRequest;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.services.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/categories")
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService categoryService;

    @PostMapping("/create")
    public BaseRespone createCategory(@RequestBody CategoryRequest request) {
        return categoryService.createCategory(request);
    }

    @GetMapping("")
    public BaseRespone getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{categoryCode}")
    public BaseRespone getCategory(@PathVariable("categoryCode") String categoryCode) {
        return categoryService.getCategoryByCode(categoryCode);
    }

    @PostMapping("/update/{categoryCode}")
    public BaseRespone updateCategory(@PathVariable("categoryCode") String categoryCode, @RequestBody CategoryRequest request) {
        return categoryService.updateCategory(categoryCode, request);
    }

    @PostMapping("/delete/{categoryCode}")
    public BaseRespone deleteCategory(@PathVariable("categoryCode") String categoryCode) {
        return categoryService.deleteCategory(categoryCode);
    }
}
