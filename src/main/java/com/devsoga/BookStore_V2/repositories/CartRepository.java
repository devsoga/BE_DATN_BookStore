package com.devsoga.BookStore_V2.repositories;

import com.devsoga.BookStore_V2.enties.CartEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {
    Optional<CartEntity> findByCustomerEntity_CustomerCodeAndProductEntity_ProductCode(String customerCode, String productCode);
    List<CartEntity> findByCustomerEntity_CustomerCode(String customerCode);
}
