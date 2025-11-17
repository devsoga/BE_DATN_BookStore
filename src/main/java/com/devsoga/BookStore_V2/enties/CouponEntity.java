package com.devsoga.BookStore_V2.enties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity(name = "coupon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CouponEntity extends BaseAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "coupon_code")
    private String couponCode;

    @Column(name = "coupon_name")
    private String couponName;

    @Column(name = "description")
    private String description;

    @Column(name = "value")
    private BigDecimal value;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    @Column(name = "status")
    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promotion_type_code", referencedColumnName = "promotion_type_code")
    private PromotionTypeEntity promotionTypeEntity;
}
