package com.devsoga.BookStore_V2.dtos.requests;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class CouponRequest {
    private String couponCode;
    private String couponName;
    private String description;
    private String promotionTypeCode;
    private BigDecimal value;
    private String startDate; // ISO_LOCAL_DATE_TIME
    private String endDate;
    private Boolean status;
}
