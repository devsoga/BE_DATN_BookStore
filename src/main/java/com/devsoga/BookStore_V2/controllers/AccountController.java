package com.devsoga.BookStore_V2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
// removed unused imports
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.devsoga.BookStore_V2.dtos.requests.RegisterRequest;
import com.devsoga.BookStore_V2.services.AccountService;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;

@RestController
@RequestMapping("/v1/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    

    @GetMapping("/{username}")
    public BaseRespone helloAccount(@PathVariable String username) {
        return accountService.getAccountDetails(username);
    }

    @PostMapping("/register")
    public BaseRespone registerAccount(@RequestBody RegisterRequest request) {
        return accountService.createAccount(request);
    }

}
