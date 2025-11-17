package com.devsoga.BookStore_V2.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailRequest {
    private String productCode;
    private Integer quantity;
    private String promotionCode; // optional per-line promotion code
    private java.math.BigDecimal discountValue; // optional suggested discount (monetary)
}
