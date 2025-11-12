package com.devsoga.BookStore_V2.repositories;

import com.devsoga.BookStore_V2.enties.SupplierEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierEntity, Integer> {
    Optional<SupplierEntity> findBySupplierCode(String supplierCode);
}
