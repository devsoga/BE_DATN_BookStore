package com.devsoga.BookStore_V2.enties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
 
import java.util.List;

@Entity(name = "import_invoice")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderEntity extends BaseAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "import_invoice_code")
    private String importInvoiceCode;

    @Column(name = "discount")
    private BigDecimal discount;

    @Column(name = "status")
    private String status; // PENDING / APPROVED / REJECTED / DELETED
    @Column(name = "reason")
    private String reason;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @Column(name = "description")
    private String description;

    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_code", referencedColumnName = "employee_code")
    private EmployeeEntity employeeEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_code", referencedColumnName = "supplier_code")
    private SupplierEntity supplierEntity;

    @OneToMany(mappedBy = "importInvoiceEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<PurchaseOrderDetailEntity> importInvoiceDetailList;
}