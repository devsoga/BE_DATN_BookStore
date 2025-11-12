package com.devsoga.BookStore_V2.controllers;

import com.devsoga.BookStore_V2.dtos.requests.InventoryDetailRequest;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.services.InventoryDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api/inventory-details")
public class InventoryDetailController {

    @Autowired
    private InventoryDetailService inventoryDetailService;

    @PostMapping("/create")
    public ResponseEntity<BaseRespone> create(@RequestBody InventoryDetailRequest req) {
        BaseRespone resp = inventoryDetailService.create(req);
        return ResponseEntity.status(resp.getStatusCode()).body(resp);
    }

    @org.springframework.web.bind.annotation.GetMapping("/by-inventory/{inventoryCode}")
    public ResponseEntity<BaseRespone> getByInventoryCode(@org.springframework.web.bind.annotation.PathVariable String inventoryCode) {
        BaseRespone resp = inventoryDetailService.getByInventoryCode(inventoryCode);
        return ResponseEntity.status(resp.getStatusCode()).body(resp);
    }
}
