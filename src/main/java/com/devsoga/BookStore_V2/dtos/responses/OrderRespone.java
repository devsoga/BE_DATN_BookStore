package com.devsoga.BookStore_V2.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderRespone {
    private String orderCode;
    private Boolean status;
    private BigDecimal discount;
    private BigDecimal totalAmount;
    private BigDecimal finalAmount;
    private String customerCode;
    private String employeeCode;
    private String promotionCode;
    private String note;
    private String address;
    private String phoneNumber;
    private Boolean isPaid;
    private java.math.BigDecimal memberDiscount;
    private java.math.BigDecimal productDiscount;
    private java.math.BigDecimal otherDiscount;
    private List<com.devsoga.BookStore_V2.dtos.responses.OrderDetailRespone> details;
}
 
