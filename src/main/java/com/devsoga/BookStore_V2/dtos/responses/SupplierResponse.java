package com.devsoga.BookStore_V2.dtos.responses;

import org.springframework.security.access.method.P;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class SupplierResponse {
    private Integer id;
    private String supplierCode;
    private String supplierName;
    private String address;
    private String description;
    private String phoneNumber;
    private String email;
    private List<SupplierProductResponse> productProvide; 
    private Boolean status;
}
