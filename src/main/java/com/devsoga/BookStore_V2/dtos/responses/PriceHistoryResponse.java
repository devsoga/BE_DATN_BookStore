package com.devsoga.BookStore_V2.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PriceHistoryResponse {
    private Integer id;
    private String priceHistoryCode;
    private BigDecimal unitPrice;
    private Boolean status;
    
    private BigDecimal importPrice;
    private String productCode;
    private String importInvoiceDetailCode;
    private LocalDateTime createdDate;
}
