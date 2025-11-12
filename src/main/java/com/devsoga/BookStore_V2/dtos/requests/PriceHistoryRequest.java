package com.devsoga.BookStore_V2.dtos.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PriceHistoryRequest {
    private String priceHistoryCode;
    private BigDecimal unitPrice;
    private Boolean status;
    private String productCode;

    @JsonProperty("importInvoiceDetailCode")
    private String importInvoiceDetailCode;

    // new field: import invoice code (allows passing import_invoice_code)
    @JsonProperty("importInvoiceCode")
    private String importInvoiceCode;
}
