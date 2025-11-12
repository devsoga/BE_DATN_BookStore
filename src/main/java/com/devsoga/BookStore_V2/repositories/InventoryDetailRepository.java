package com.devsoga.BookStore_V2.repositories;

import com.devsoga.BookStore_V2.enties.InventoryDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryDetailRepository extends JpaRepository<InventoryDetailEntity, Integer> {
    Optional<InventoryDetailEntity> findByInventoryDetailCode(String inventoryDetailCode);
    java.util.List<InventoryDetailEntity> findByInventoryEntity_InventoryCode(String inventoryCode);
    java.util.List<InventoryDetailEntity> findByImportInvoiceDetailEntity_ImportInvoiceDetailCode(String importInvoiceDetailCode);
}
