package com.devsoga.BookStore_V2.enties;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JsonIgnore
    private List<CustomerEntity> customerList;
}
