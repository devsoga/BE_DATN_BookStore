package com.devsoga.BookStore_V2.dtos.responses;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class AccountRespone {
    private String accountCode;
    private String customerCode;
    private String customerName;
    private String employeeCode;
    private String employeeName;
    private String promotion_code;
    private String customerTypeCode;
    private String customerTypeName;
    private BigDecimal memberDiscount;
    private BigDecimal points;
    private String username;
    private String email;
    private String roleCode;
    private String accessToken;
    private String refreshToken;
    
}   
