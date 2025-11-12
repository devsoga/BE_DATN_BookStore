package com.devsoga.BookStore_V2.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PurchaseOrderDetailResponse {
    private Integer id;
    private String importInvoiceDetailCode;
    private Integer quantity;
    private Integer quantitySold;
    private BigDecimal importPrice;
    private BigDecimal totalAmount;
    private String productCode;
    private LocalDateTime createdDate;
    private Integer quantityCancel;
}
