package com.devsoga.BookStore_V2.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SupplierRequest {
    private String supplierCode;
    private String supplierName;
    private String address;
    private String description;
    private String phoneNumber;
    private String email;
    private Boolean status; // optional
    private java.util.List<com.devsoga.BookStore_V2.dtos.requests.SupplierProductRequest> productProvide; // optional list of supplier products to create/update
}
