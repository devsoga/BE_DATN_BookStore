package com.devsoga.BookStore_V2.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CancelledProductResponse {
    private Integer id;
    private String cancelledProductCode;
    private Integer quantity;
    private String importInvoiceCode;
    private String productCode;
    private String importInvoiceDetailCode;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private String reason;
}
