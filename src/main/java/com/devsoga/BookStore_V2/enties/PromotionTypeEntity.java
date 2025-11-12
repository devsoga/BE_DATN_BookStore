package com.devsoga.BookStore_V2.enties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity(name = "promotion_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PromotionTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "promotion_type_code")
    private String promotionTypeCode;

    @Column(name = "promotion_type_name")
    private String promotionTypeName;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "promotionTypeEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PromotionEntity> promotionList;
}