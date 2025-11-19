package com.devsoga.BookStore_V2.enties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@Entity(name = "`order`")
@Table(name = "`order`")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceEntity extends BaseAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "order_code")
    private String orderCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "order_type")
    private OrderType orderType;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;


    @Column(name = "promotion_customer_value")
    private BigDecimal promotionCustomerValue;

    @Column(name = "promotion_customer_code")
    private String promotionCustomerCode;

    @Column(name = "coupon_discount_value")
    private BigDecimal couponDiscountValue;

    @Column(name = "coupon_code")
    private String couponCode;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "final_amount")
    private BigDecimal finalAmount;
    
    @Column(name = "note")
    private String note;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column(name = "is_paid")
    private Boolean isPaid = Boolean.FALSE;

    @Column(name = "order_status")
    private String orderStatus = "pending";

    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_code", referencedColumnName = "customer_code")
    private CustomerEntity customerEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_code", referencedColumnName = "employee_code")
    private EmployeeEntity employeeEntity;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InvoiceDetailEntity> orderDetailList;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ReturnOrderEntity> returnOrderList;


    // Enums
    public enum OrderType {
        Offline, Online
    }

    public enum PaymentMethod {
        Cash, QR
    }
    
}