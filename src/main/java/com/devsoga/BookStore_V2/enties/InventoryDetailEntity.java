package com.devsoga.BookStore_V2.enties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "inventory_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InventoryDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "inventory_detail_code")
    private String inventoryDetailCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_code", referencedColumnName = "inventory_code")
    private InventoryEntity inventoryEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "import_invoice_code", referencedColumnName = "import_invoice_code")
    private PurchaseOrderEntity importInvoiceEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "import_invoice_detail_code", referencedColumnName = "import_invoice_detail_code")
    private PurchaseOrderDetailEntity importInvoiceDetailEntity;

    @Column(name = "quantity")
    private Integer quantity;
}
