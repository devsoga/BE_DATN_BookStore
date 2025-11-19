package com.devsoga.BookStore_V2.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsoga.BookStore_V2.enties.CustomerTypeEntity;

@Repository
public interface CustomerTypeRepository extends JpaRepository<CustomerTypeEntity, Integer> {
    Optional<CustomerTypeEntity> findByCustomerTypeCode(String customerTypeCode);
    List<CustomerTypeEntity> findByCustomerTypeNameContainingIgnoreCase(String customerTypeName);
}
