package com.devsoga.BookStore_V2.dtos.responses;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
public class PromotionResponse {
    private Integer id;
    private String promotionCode;
    private String promotionName;
    private BigDecimal value;
    private String promotionTypeCode;
    private String promotionTypeName;
    private Timestamp startDate;
    private Timestamp endDate;
    private Boolean status;
}
