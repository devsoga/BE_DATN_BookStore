package com.devsoga.BookStore_V2.dtos.responses;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRespone {
    private Integer id;
    private String productCode;
    private String productName;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalAmount;
    private String image;
    private String promotionCode;
    private BigDecimal discountValue;
}
