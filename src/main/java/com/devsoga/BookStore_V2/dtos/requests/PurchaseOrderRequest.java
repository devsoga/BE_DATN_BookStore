package com.devsoga.BookStore_V2.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PurchaseOrderRequest {
    private String importInvoiceCode;
    private BigDecimal discount;
    // status: PENDING | APPROVED | REJECTED | DELETED
    private String status;
    private BigDecimal totalAmount;
    private String description;
    private String employeeCode;
    private String supplierCode;
    private String reason;
    private List<PurchaseOrderDetailRequest> details;
}
