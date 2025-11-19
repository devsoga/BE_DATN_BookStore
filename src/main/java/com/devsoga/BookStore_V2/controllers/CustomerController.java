package com.devsoga.BookStore_V2.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsoga.BookStore_V2.enties.CustomerEntity;
import com.devsoga.BookStore_V2.services.CustomerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/api/customers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // cho phép Flutter/React call thoải mái
public class CustomerController {

    private final CustomerService customerService;

    // ============= CREATE =============
    @PostMapping
    public ResponseEntity<CustomerEntity> create(@RequestBody CustomerEntity customer) {
        return ResponseEntity.ok(customerService.create(customer));
    }

    // ============= UPDATE =============
    @PutMapping("/{id}")
    public ResponseEntity<CustomerEntity> update(
            @PathVariable Integer id,
            @RequestBody CustomerEntity customer) {
        return ResponseEntity.ok(customerService.update(id, customer));
    }

    // ============= DELETE =============
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ============= GET ONE BY ID =============
    @GetMapping("/{id}")
    public ResponseEntity<CustomerEntity> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(customerService.getById(id));
    }

    // ============= GET ALL =============
    @GetMapping
    public ResponseEntity<List<CustomerEntity>> getAll() {
        return ResponseEntity.ok(customerService.getAll());
    }

    // ============= GET BY customer_code =============
    // /v1/api/customers/by-code?code=CUS001
    @GetMapping("/by-code")
    public ResponseEntity<CustomerEntity> getByCode(@RequestParam("code") String code) {
        return ResponseEntity.ok(customerService.getByCode(code));
    }

    // ============= SEARCH BY NAME =============
    // /v1/api/customers/search?name=trung
    @GetMapping("/search")
    public ResponseEntity<List<CustomerEntity>> searchByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(customerService.searchByName(name));
    }
}
