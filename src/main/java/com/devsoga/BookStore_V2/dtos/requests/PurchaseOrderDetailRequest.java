package com.devsoga.BookStore_V2.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PurchaseOrderDetailRequest {
    private String importInvoiceDetailCode;
    private Integer quantity;
    private BigDecimal importPrice;
    private BigDecimal totalAmount;
    private String productCode;
}
