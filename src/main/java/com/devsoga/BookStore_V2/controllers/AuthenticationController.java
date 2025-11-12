package com.devsoga.BookStore_V2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsoga.BookStore_V2.dtos.requests.LoginRequest;
import com.devsoga.BookStore_V2.payload.respone.BaseRespone;
import com.devsoga.BookStore_V2.services.AuthenticationService;

@RestController
@RequestMapping("/v1/api/auth")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public BaseRespone loginAccount(@RequestBody LoginRequest request) {
       return authenticationService.login(request);
    }
}
