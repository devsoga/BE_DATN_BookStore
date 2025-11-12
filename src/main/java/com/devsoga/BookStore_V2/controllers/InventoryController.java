package com.devsoga.BookStore_V2.controllers;

import com.devsoga.BookStore_V2.dtos.requests.InventoryCreateRequest;
import com.devsoga.BookStore_V2.dtos.requests.InventoryRequest;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.services.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("")
    public ResponseEntity<BaseRespone> getAll() {
        BaseRespone resp = inventoryService.getAllInventories();
        return ResponseEntity.status(resp.getStatusCode()).body(resp);
    }

    @GetMapping("/by-product/{productCode}")
    public ResponseEntity<BaseRespone> getByProduct(@PathVariable String productCode) {
        BaseRespone resp = inventoryService.getByProductCode(productCode);
        return ResponseEntity.status(resp.getStatusCode()).body(resp);
    }

    @GetMapping("/{inventoryCode}")
    public ResponseEntity<BaseRespone> getByCode(@PathVariable String inventoryCode) {
        BaseRespone resp = inventoryService.getByInventoryCode(inventoryCode);
        return ResponseEntity.status(resp.getStatusCode()).body(resp);
    }

    @PostMapping("")
    public ResponseEntity<BaseRespone> create(@Valid @RequestBody InventoryCreateRequest req) {
        BaseRespone resp = inventoryService.createInventory(req);
        return ResponseEntity.status(resp.getStatusCode()).body(resp);
    }

    @PutMapping("/{inventoryCode}")
    public ResponseEntity<BaseRespone> update(@PathVariable String inventoryCode, @Valid @RequestBody InventoryRequest req) {
        BaseRespone resp = inventoryService.updateInventory(inventoryCode, req);
        return ResponseEntity.status(resp.getStatusCode()).body(resp);
    }

    @DeleteMapping("/{inventoryCode}")
    public ResponseEntity<BaseRespone> delete(@PathVariable String inventoryCode) {
        BaseRespone resp = inventoryService.deleteInventory(inventoryCode);
        return ResponseEntity.status(resp.getStatusCode()).body(resp);
    }
}
