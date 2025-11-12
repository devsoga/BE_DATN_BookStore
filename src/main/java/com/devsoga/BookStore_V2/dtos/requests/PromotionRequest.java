package com.devsoga.BookStore_V2.dtos.requests;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class PromotionRequest {
    private String promotionCode;
    private String promotionName;
    private BigDecimal value;
    private String promotionTypeCode;
    private String startDate; // ISO_LOCAL_DATE_TIME expected (e.g. 2023-01-02T15:30:00)
    private String endDate;
    private Boolean status;
}
