package com.devsoga.BookStore_V2.repositories;

import com.devsoga.BookStore_V2.enties.CancelledProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface CancelledProductRepository extends JpaRepository<CancelledProductEntity, Integer> {
    Optional<CancelledProductEntity> findByCancelledProductCode(String cancelledProductCode);
    
    List<CancelledProductEntity> findByImportInvoiceEntity_ImportInvoiceCode(String importInvoiceCode);
    
    List<CancelledProductEntity> findByProductEntity_ProductCode(String productCode);
    
    List<CancelledProductEntity> findByImportInvoiceDetailEntity_ImportInvoiceDetailCode(String importInvoiceDetailCode);

    // Use the entity name declared in @Entity(name = "cancelled_product")
    @Query("SELECT COALESCE(SUM(c.quantity), 0) FROM cancelled_product c WHERE c.importInvoiceDetailEntity.importInvoiceDetailCode = :detailCode")
    Long sumQuantityByImportInvoiceDetailCode(@Param("detailCode") String detailCode);
}
