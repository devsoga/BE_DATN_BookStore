package com.devsoga.BookStore_V2.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class SupplierProductResponse {
    private Integer id;
    private String supplierProductCode;
    private String supplierCode;
    private String productCode;
    private String productName;
    private String categoryName;
    private String image;
    private BigDecimal importPrice;
    private Boolean status;
    private LocalDateTime createdDate;
}
