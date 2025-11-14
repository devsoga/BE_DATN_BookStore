package com.devsoga.BookStore_V2.repositories;

import com.devsoga.BookStore_V2.enties.SupplierProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierProductRepository extends JpaRepository<SupplierProductEntity, Integer> {
    Optional<SupplierProductEntity> findBySupplierProductCode(String code);
    java.util.List<SupplierProductEntity> findBySupplierCode(String supplierCode);
    java.util.Optional<SupplierProductEntity> findBySupplierCodeAndProductCode(String supplierCode, String productCode);
    java.util.List<SupplierProductEntity> findByProductCode(String productCode);
    java.util.List<SupplierProductEntity> findBySupplierCodeAndStatus(String supplierCode, Boolean status);
}
