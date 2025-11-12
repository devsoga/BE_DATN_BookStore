package com.devsoga.BookStore_V2.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryDetailRequest {
    private String inventoryDetailCode;
    private String inventoryCode;
    private String importInvoiceCode;
    private String importInvoiceDetailCode;
    private Integer quantity;
}
