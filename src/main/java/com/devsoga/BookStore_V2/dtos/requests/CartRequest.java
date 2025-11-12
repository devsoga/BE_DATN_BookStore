package com.devsoga.BookStore_V2.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequest {
    private String customerCode;
    private String productCode;
    private Integer quantity;
}
