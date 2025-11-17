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
    private String promotionCustomerCode; // optional - promotion code for customer type
    private BigDecimal promotionCustomerValue; // optional - promotion value for customer type (0.10 = 10%)
    private String couponCode; // optional - coupon code for order-level discount
    private BigDecimal couponDiscountValue; // optional - coupon discount value
    private String orderType; // Offline or Online
    private String paymentMethod; // Cash or QR
    private BigDecimal discount; // optional - manual discount
    private String note; // optional order note
    private String address; // optional shipping address
    private String phoneNumber; // optional receiver phone number
    private List<OrderDetailRequest> details;

    // Manual getters for compilation workaround
    public String getCustomerCode() { return customerCode; }
    public String getEmployeeCode() { return employeeCode; }
    public String getPromotionCustomerCode() { return promotionCustomerCode; }
    public BigDecimal getPromotionCustomerValue() { return promotionCustomerValue; }
    public String getCouponCode() { return couponCode; }
    public BigDecimal getCouponDiscountValue() { return couponDiscountValue; }
    public String getOrderType() { return orderType; }
    public String getPaymentMethod() { return paymentMethod; }
    public BigDecimal getDiscount() { return discount; }
    public String getNote() { return note; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }
    public List<OrderDetailRequest> getDetails() { return details; }
}

