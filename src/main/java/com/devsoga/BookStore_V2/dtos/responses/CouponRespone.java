package com.devsoga.BookStore_V2.dtos.responses;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
public class CouponRespone {
    private int id;
    private String couponCode;
    private String couponName;
    private String description;
    private BigDecimal value;
    private Timestamp startDate;
    private Timestamp endDate;
    private Boolean status;
    private String promotionTypeCode;
    private String promotionTypeName;
}
