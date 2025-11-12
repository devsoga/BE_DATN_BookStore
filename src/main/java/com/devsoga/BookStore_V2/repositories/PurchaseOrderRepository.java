package com.devsoga.BookStore_V2.repositories;

import com.devsoga.BookStore_V2.enties.PurchaseOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrderEntity, Integer> {
    Optional<PurchaseOrderEntity> findByImportInvoiceCode(String importInvoiceCode);

    @org.springframework.data.jpa.repository.Query("SELECT DISTINCT p FROM import_invoice p JOIN p.importInvoiceDetailList d WHERE d.productEntity.productCode = :productCode")
    List<PurchaseOrderEntity> findByProductCode(@org.springframework.data.repository.query.Param("productCode") String productCode);
}
