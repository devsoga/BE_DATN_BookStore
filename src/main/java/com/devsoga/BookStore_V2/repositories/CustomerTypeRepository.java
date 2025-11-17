package com.devsoga.BookStore_V2.repositories;

import com.devsoga.BookStore_V2.enties.CustomerTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerTypeRepository extends JpaRepository<CustomerTypeEntity, Integer> {
    Optional<CustomerTypeEntity> findByCustomerTypeCode(String customerTypeCode);
}
