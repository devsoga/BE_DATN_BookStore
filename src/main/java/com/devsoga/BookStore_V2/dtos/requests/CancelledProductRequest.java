package com.devsoga.BookStore_V2.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelledProductRequest {
    private String cancelledProductCode;
    private Integer quantity;
    private String reason;
    private String importInvoiceCode;
    private String productCode;
    private String importInvoiceDetailCode;
}
