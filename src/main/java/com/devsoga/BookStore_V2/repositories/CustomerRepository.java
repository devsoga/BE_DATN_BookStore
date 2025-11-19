package com.devsoga.BookStore_V2.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsoga.BookStore_V2.enties.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {

   
    Optional<CustomerEntity> findByCustomerCode(String customerCode);

    List<CustomerEntity> findByCustomerNameContainingIgnoreCase(String customerName);
    
    // helper to find customers linked to an account code
    List<CustomerEntity> findByAccountEntity_AccountCode(String accountCode);
}
