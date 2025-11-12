package com.devsoga.BookStore_V2.dtos.requests;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class OrderPromotionRequest {
    private String promotionCode;
    private BigDecimal manualDiscountAmount; // For manual discount override
}
