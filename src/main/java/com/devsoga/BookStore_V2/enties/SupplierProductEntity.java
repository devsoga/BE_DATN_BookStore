package com.devsoga.BookStore_V2.enties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "supplier_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierProductEntity extends BaseAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "supplier_product_code")
    private String supplierProductCode;

    @Column(name = "supplier_code")
    private String supplierCode;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "import_price")
    private java.math.BigDecimal importPrice;

    @Column(name = "status")
    private Boolean status;
}
