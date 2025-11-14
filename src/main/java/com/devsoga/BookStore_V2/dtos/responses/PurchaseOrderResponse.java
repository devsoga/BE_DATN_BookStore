package com.devsoga.BookStore_V2.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.time.LocalDateTime;

@Getter
@Setter
public class PurchaseOrderResponse {
    private Integer id;
    private String importInvoiceCode;
    private BigDecimal discount;
    private String status;
    private BigDecimal totalAmount;
    private String description;
    private String employeeCode;
    private String supplierCode;
    private String supplierName;
    private List<PurchaseOrderDetailResponse> details;
    private LocalDateTime createdDate;
    private String reason;
}
