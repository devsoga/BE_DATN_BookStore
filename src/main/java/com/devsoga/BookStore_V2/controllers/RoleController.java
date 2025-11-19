// File: src/main/java/com/devsoga/BookStore_V2/controllers/RoleController.java
package com.devsoga.BookStore_V2.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsoga.BookStore_V2.enties.RoleEntity;
import com.devsoga.BookStore_V2.services.RoleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/api/roles")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<RoleEntity> create(@RequestBody RoleEntity role) {
        return ResponseEntity.ok(roleService.create(role));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoleEntity> update(@PathVariable("id") Integer id,
                                             @RequestBody RoleEntity role) {
        return ResponseEntity.ok(roleService.update(id, role));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleEntity> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(roleService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<RoleEntity>> getAll() {
        return ResponseEntity.ok(roleService.getAll());
    }

    @GetMapping("/by-code")
    public ResponseEntity<RoleEntity> getByCode(@RequestParam("code") String code) {
        return ResponseEntity.ok(roleService.getByCode(code));
    }

    @GetMapping("/by-name")
    public ResponseEntity<RoleEntity> getByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(roleService.getByName(name));
    }
}
