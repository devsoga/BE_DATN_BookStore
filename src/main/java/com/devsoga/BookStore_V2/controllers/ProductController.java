package com.devsoga.BookStore_V2.controllers;

import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.services.ProductService;
import com.devsoga.BookStore_V2.dtos.requests.ProductRequest;
import com.devsoga.BookStore_V2.dtos.requests.ProductCreateRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // GET /api/products - list all products
    @GetMapping("")
    public BaseRespone getAllProducts() {
        return productService.getAllProducts();
    }

    // GET /api/products/{productCode} - get single product by code
    @GetMapping("/{productCode}")
    public BaseRespone getProductByProductCode(@PathVariable("productCode") String productCode) {
        return productService.getProductByProductCode(productCode);
    }


    // GET /v1/api/products/search?q=keyword - search products by name, description or code
    @GetMapping("/search")
    public BaseRespone searchProducts(@org.springframework.web.bind.annotation.RequestParam(value = "q", required = false) String q) {
        return productService.searchProducts(q);
    }

    // GET /v1/api/products/by-category/{categoryCode} - get products by category
    @GetMapping("/by-category/{categoryCode}")
    public BaseRespone getProductsByCategory(@PathVariable("categoryCode") String categoryCode) {
        return productService.getProductsByCategoryCode(categoryCode);
    }

    // GET /v1/api/products/by-category-type/{categoryType} - get products by category type
    @GetMapping("/by-category-type/{categoryType}")
    public BaseRespone getProductsByCategoryType(@PathVariable("categoryType") String categoryType) {
        return productService.getProductsByCategoryType(categoryType);
    }

    // POST /v1/api/products - create a new product
    @PostMapping("/create")
    public BaseRespone createProduct(@Valid @RequestBody ProductCreateRequest req) {
        return productService.createProduct(req);
    }

    // PUT /v1/api/products/{productCode} - update product
    @PostMapping("update/{productCode}")
    public BaseRespone updateProduct(@PathVariable("productCode") String productCode, @Valid @RequestBody ProductRequest req) {
        return productService.updateProduct(productCode, req);
    }

    // DELETE /v1/api/products/{productCode} - delete product
    @PostMapping("delete/{productCode}")
    public BaseRespone deleteProduct(@PathVariable("productCode") String productCode) {
        return productService.deleteProduct(productCode);
    }


    


}
