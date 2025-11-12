package com.devsoga.BookStore_V2.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InventoryDetailResponse {
    private Integer id;
    private String inventoryDetailCode;
    private String inventoryCode;
    private String importInvoiceCode;
    private String importInvoiceDetailCode;
    private Integer quantity;
}
