package com.devsoga.BookStore_V2.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequest {
    private String username;
    private String password;
    private String email;
    private String accountCode; // optional, will be generated if missing
    private String roleCode; // optional, default to 'USER' when missing
}
