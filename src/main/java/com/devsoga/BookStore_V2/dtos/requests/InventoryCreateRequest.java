package com.devsoga.BookStore_V2.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InventoryCreateRequest {
    @NotBlank(message = "inventoryCode is required")
    private String inventoryCode;
    private Boolean status;

    private String productCode;

}
