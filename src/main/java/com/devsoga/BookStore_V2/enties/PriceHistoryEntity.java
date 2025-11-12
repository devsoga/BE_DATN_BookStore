package com.devsoga.BookStore_V2.enties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
 

@Entity(name = "price_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PriceHistoryEntity extends BaseAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "price_history_code")
    private String priceHistoryCode;

    @Column(name = "unit_price")
    private BigDecimal unitPrice;

    @Column(name = "status")
    private Boolean status;

    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_code", referencedColumnName = "product_code")
    private ProductEntity productEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "import_invoice_detail_code", referencedColumnName = "import_invoice_detail_code")
    private PurchaseOrderDetailEntity importInvoiceDetailEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "import_invoice_code", referencedColumnName = "import_invoice_code")
    private PurchaseOrderEntity importInvoiceEntity;
}