package com.devsoga.BookStore_V2.enties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "cancelled_product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CancelledProductEntity extends BaseAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "cancelled_product_code")
    private String cancelledProductCode;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "reason")
    private String reason;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "import_invoice_code", referencedColumnName = "import_invoice_code")
    private PurchaseOrderEntity importInvoiceEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_code", referencedColumnName = "product_code")
    private ProductEntity productEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "import_invoice_detail_code", referencedColumnName = "import_invoice_detail_code")
    private PurchaseOrderDetailEntity importInvoiceDetailEntity;
}
