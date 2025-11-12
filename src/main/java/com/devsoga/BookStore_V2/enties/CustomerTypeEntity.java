package com.devsoga.BookStore_V2.enties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity(name = "customer_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "customer_type_code")
    private String customerTypeCode;

    @Column(name = "customer_type_name")
    private String customerTypeName;

    @Column(name = "description")
    private String description;

    @Column(name = "promotion_code")
    private String promotionCode;

    @OneToMany(mappedBy = "customerTypeEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CustomerEntity> customerList;
}
