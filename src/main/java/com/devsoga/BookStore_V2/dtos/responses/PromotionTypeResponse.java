package com.devsoga.BookStore_V2.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromotionTypeResponse {
    private Integer id;
    private String promotionTypeCode;
    private String promotionTypeName;
    private String description;
}
