package com.devsoga.BookStore_V2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.devsoga.BookStore_V2.enties.InventoryEntity;

@Repository
public interface InventoryRepository extends JpaRepository<InventoryEntity, Integer> {

    @Query(value = "SELECT COALESCE(SUM(idt.quantity),0) FROM inventory_detail idt JOIN inventory i ON idt.inventory_code = i.inventory_code WHERE i.product_code = :productCode AND i.status = 1", nativeQuery = true)
    Integer findTotalQuantityByProductCode(@Param("productCode") String productCode);

    java.util.Optional<InventoryEntity> findByInventoryCode(String inventoryCode);

    // find all inventory rows for a product code with status = true
    java.util.List<InventoryEntity> findByProductEntity_ProductCodeAndStatus(String productCode, Boolean status);
}
