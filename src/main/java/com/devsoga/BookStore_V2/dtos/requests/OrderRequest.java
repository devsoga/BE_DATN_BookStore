package com.devsoga.BookStore_V2.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderRequest {
    private String customerCode;
    private String employeeCode; // optional
    private java.util.List<String> promotionCodes; // optional - allow multiple promotion codes
    private String orderType; // Offline or Online
    private String paymentMethod; // Cash or QR
    private BigDecimal discount; // optional
    private String note; // optional order note
    private String address; // optional shipping address
    private String phoneNumber; // optional receiver phone number
    private List<OrderDetailRequest> details;

    // Manual getters for compilation workaround
    public String getCustomerCode() { return customerCode; }
    public String getEmployeeCode() { return employeeCode; }
    public java.util.List<String> getPromotionCodes() { return promotionCodes; }
    public String getOrderType() { return orderType; }
    public String getPaymentMethod() { return paymentMethod; }
    public BigDecimal getDiscount() { return discount; }
    public String getNote() { return note; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
    public List<OrderDetailRequest> getDetails() { return details; }
}

