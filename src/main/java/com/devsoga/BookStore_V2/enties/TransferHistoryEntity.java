package com.devsoga.BookStore_V2.enties;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "transfer_history")
public class TransferHistoryEntity extends BaseAuditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_code", nullable = false)
    private String orderCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method", nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "transferAmount", nullable = false, precision = 20, scale = 2)
    private BigDecimal transferAmount;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    public enum PaymentMethod {
        QR, VNPAY, Momo
    }

    // Constructors
    public TransferHistoryEntity() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getOrderCode() { return orderCode; }
    public void setOrderCode(String orderCode) { this.orderCode = orderCode; }

    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(PaymentMethod paymentMethod) { this.paymentMethod = paymentMethod; }

    public BigDecimal getTransferAmount() { return transferAmount; }
    public void setTransferAmount(BigDecimal transferAmount) { this.transferAmount = transferAmount; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}