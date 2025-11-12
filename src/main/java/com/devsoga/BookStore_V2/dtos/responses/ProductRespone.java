package com.devsoga.BookStore_V2.dtos.responses;

import java.math.BigDecimal;

import com.devsoga.BookStore_V2.dtos.responses.PromotionResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRespone {
    
    private String productCode;
    private String productName;
    private String description;
    private String image;
    private String author;
    private String publisher;
    private BigDecimal price;
    private BigDecimal importPrice;
    private String promotionCode;
    private String promotionName;
    private PromotionResponse promotion;
    private String categoryCode;
    private String categoryName;
    private Integer stockQuantity;
}
