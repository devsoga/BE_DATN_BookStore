package com.devsoga.BookStore_V2.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InventoryResponse {
    private String inventoryCode;
    private Boolean status;
    // nested product object (only productCode and productName)
    private InventoryProductDto product;
    private Integer quantityOnHand;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
