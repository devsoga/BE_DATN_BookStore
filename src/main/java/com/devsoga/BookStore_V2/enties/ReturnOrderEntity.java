package com.devsoga.BookStore_V2.enties;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "return_exchange")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnOrderEntity extends BaseAuditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "return_exchange_code")
    private String returnExchangeCode;

    @Column(name = "reason")
    private String reason;

    @Column(name = "status")
    private Boolean status;

    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_code", referencedColumnName = "order_code")
    private InvoiceEntity orderEntity;
}
