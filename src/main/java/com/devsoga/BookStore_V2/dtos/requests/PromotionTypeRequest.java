package com.devsoga.BookStore_V2.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PromotionTypeRequest {
    private String promotionTypeCode;
    private String promotionTypeName;
    private String description;
}
