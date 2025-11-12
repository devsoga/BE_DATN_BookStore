package com.devsoga.BookStore_V2.controllers;

import com.devsoga.BookStore_V2.dtos.requests.EmployeeAccountRequest;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.services.EmployeeAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/employee/accounts")
public class EmployeeAccountController {

    @Autowired
    private EmployeeAccountService employeeAccountService;

    @GetMapping("")
    public BaseRespone getAll() {
        return employeeAccountService.getAllEmployeeAccounts();
    }

    @GetMapping("/{employeeCode}")
    public BaseRespone getByEmployeeCode(@PathVariable("employeeCode") String employeeCode) {
        return employeeAccountService.getByEmployeeCode(employeeCode);
    }

    @PostMapping("/create")
    public BaseRespone create(@RequestBody EmployeeAccountRequest req) {
        return employeeAccountService.createEmployeeAccount(req);
    }

    @PostMapping("/update/{accountCode}")
    public BaseRespone update(@PathVariable("accountCode") String accountCode, @RequestBody EmployeeAccountRequest req) {
        return employeeAccountService.updateEmployeeAccount(accountCode, req);
    }

    @PostMapping("/delete/{accountCode}")
    public BaseRespone delete(@PathVariable("accountCode") String accountCode) {
        return employeeAccountService.deleteEmployeeAccount(accountCode);
    }
}
