package com.devsoga.BookStore_V2.repositories;

import com.devsoga.BookStore_V2.enties.PriceHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface PriceHistoryRepository extends JpaRepository<PriceHistoryEntity, Integer> {
    
    @Query(value = "SELECT unit_price FROM price_history WHERE product_code = :productCode AND status = 1 ORDER BY created_date DESC LIMIT 1", nativeQuery = true)
    Optional<BigDecimal> findLatestActivePriceByProductCode(@Param("productCode") String productCode);

    @Query(value = "SELECT ph.* FROM price_history ph JOIN import_invoice_detail iid ON ph.import_invoice_detail_code = iid.import_invoice_detail_code JOIN import_invoice ii ON iid.import_invoice_code = ii.import_invoice_code WHERE ii.import_invoice_code = :importInvoiceCode ORDER BY ph.created_date DESC", nativeQuery = true)
    java.util.List<PriceHistoryEntity> findByImportInvoiceCode(@Param("importInvoiceCode") String importInvoiceCode);

    @Query("SELECT p FROM price_history p WHERE p.productEntity.productCode = :productCode AND p.status = true ORDER BY p.createdDate DESC")
    java.util.List<PriceHistoryEntity> findActiveByProductCode(@Param("productCode") String productCode);

    // find all price history rows for a given product (regardless of status)
    java.util.List<PriceHistoryEntity> findByProductEntity_ProductCode(String productCode);

    java.util.Optional<PriceHistoryEntity> findByPriceHistoryCode(String priceHistoryCode);
}