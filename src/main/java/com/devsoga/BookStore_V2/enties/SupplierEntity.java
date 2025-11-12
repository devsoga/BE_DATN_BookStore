package com.devsoga.BookStore_V2.enties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Entity(name = "supplier")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierEntity extends BaseAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "supplier_code")
    private String supplierCode;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "address")
    private String address;

    @Column(name = "description")
    private String description;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private Boolean status;
    @OneToMany(mappedBy = "supplierEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PurchaseOrderEntity> importInvoiceList;
}
