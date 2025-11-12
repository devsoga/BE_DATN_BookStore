package com.devsoga.BookStore_V2.repositories;

import com.devsoga.BookStore_V2.enties.PurchaseOrderDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface PurchaseOrderDetailRepository extends JpaRepository<PurchaseOrderDetailEntity, Integer> {
    
    @Query(value = "SELECT import_price FROM import_invoice_detail WHERE product_code = :productCode ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Optional<BigDecimal> findLatestImportPriceByProductCode(@Param("productCode") String productCode);
    
    Optional<PurchaseOrderDetailEntity> findByImportInvoiceDetailCode(String importInvoiceDetailCode);

    // find a specific import_invoice_detail by import invoice code and product code
    Optional<PurchaseOrderDetailEntity> findByImportInvoiceEntity_ImportInvoiceCodeAndProductEntity_ProductCode(String importInvoiceCode, String productCode);
    
    // find all import_invoice_detail for a product from approved invoices, ordered by creation date (FIFO)
    java.util.List<PurchaseOrderDetailEntity> findByProductEntity_ProductCodeAndImportInvoiceEntity_StatusOrderByCreatedDateAsc(String productCode, String status);
}
