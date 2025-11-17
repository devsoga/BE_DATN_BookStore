package com.devsoga.BookStore_V2.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderRespone {
    private String orderCode;
    private String orderType;
    private String paymentMethod;
    private Boolean status;
    private Boolean isPaid;
    private LocalDateTime orderDate;
    private BigDecimal promotionCustomerValue; // customer type based discount
    private BigDecimal couponDiscountValue; // coupon based discount
    private BigDecimal discount; // additional manual discount
    private BigDecimal totalAmount;
    private BigDecimal finalAmount;
    private String customerCode;
    private String employeeCode;
    private String note;
    private String address;
    private String phoneNumber;
    private List<com.devsoga.BookStore_V2.dtos.responses.OrderDetailRespone> details;
}
 
