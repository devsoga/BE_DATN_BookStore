// File: src/main/java/com/devsoga/BookStore_V2/controllers/EmployeeController.java
package com.devsoga.BookStore_V2.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsoga.BookStore_V2.enties.EmployeeEntity;
import com.devsoga.BookStore_V2.services.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/api/employees")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeEntity> create(@RequestBody EmployeeEntity employee) {
        return ResponseEntity.ok(employeeService.create(employee));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeEntity> update(@PathVariable("id") Integer id,
                                                 @RequestBody EmployeeEntity employee) {
        return ResponseEntity.ok(employeeService.update(id, employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEntity> getById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(employeeService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeEntity>> getAll() {
        return ResponseEntity.ok(employeeService.getAll());
    }

    @GetMapping("/by-code")
    public ResponseEntity<EmployeeEntity> getByCode(@RequestParam("code") String code) {
        return ResponseEntity.ok(employeeService.getByCode(code));
    }

    @GetMapping("/search")
    public ResponseEntity<List<EmployeeEntity>> searchByName(@RequestParam("name") String name) {
        return ResponseEntity.ok(employeeService.searchByName(name));
    }
}
