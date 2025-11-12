package com.devsoga.BookStore_V2.dtos.responses;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FavoriteRespone {
    private Integer id;
    private String productCode;
    private String productName;
    private String image;
    private BigDecimal unitPrice;
    private LocalDateTime createdDate;

}
