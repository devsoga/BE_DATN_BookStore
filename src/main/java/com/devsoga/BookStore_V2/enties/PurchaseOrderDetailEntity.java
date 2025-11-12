package com.devsoga.BookStore_V2.enties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

@Entity(name = "import_invoice_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderDetailEntity extends BaseAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "import_invoice_detail_code")
    private String importInvoiceDetailCode;

    @Column(name = "quantity_imported")
    private Integer quantityImported;

    @Column(name = "quantity_sold")
    private Integer quantitySold;

    @Column(name = "cancelled_quantity")
    private Integer cancelledQuantity;

    // Backwards-compatible accessors for existing code that expects getQuantity()/setQuantity()
    public Integer getQuantity() {
        return this.quantityImported;
    }

    public void setQuantity(Integer quantity) {
        this.quantityImported = quantity;
    }

    @Column(name = "import_price")
    private BigDecimal importPrice;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_code", referencedColumnName = "product_code")
    private ProductEntity productEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "import_invoice_code", referencedColumnName = "import_invoice_code")
    private PurchaseOrderEntity importInvoiceEntity;

    @OneToMany(mappedBy = "importInvoiceDetailEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PriceHistoryEntity> priceHistoryList;
}