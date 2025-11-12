package com.devsoga.BookStore_V2.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeAccountRequest {
    // account fields
    private String username;
    private String password;
    private String email;
    private String roleCode; // e.g., 'EMP' or other staff role
    private String accountCode; // optional

    // employee fields
    private String employeeCode; // optional, will be generated
    private String employeeName;
    private String birthDate; // ISO yyyy-MM-dd
    private Boolean gender; // true = male, false = female
}
