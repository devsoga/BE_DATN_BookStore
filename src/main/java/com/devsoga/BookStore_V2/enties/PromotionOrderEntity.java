package com.devsoga.BookStore_V2.enties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "promotion_order")
@Table(name = "promotion_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromotionOrderEntity extends BaseAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "promotion_order_code")
    private String promotionOrderCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_code", referencedColumnName = "order_code")
    private InvoiceEntity orderEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_code", referencedColumnName = "promotion_code")
    private PromotionEntity promotionEntity;

    @Column(name = "discount_amount")
    private BigDecimal discountAmount;
}
