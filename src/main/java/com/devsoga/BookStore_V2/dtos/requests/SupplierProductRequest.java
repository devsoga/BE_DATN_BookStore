package com.devsoga.BookStore_V2.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class SupplierProductRequest {
    private String supplierProductCode;
    private String supplierCode;
    private String productCode;
    private BigDecimal importPrice;
    private Boolean status;
}
