package com.devsoga.BookStore_V2.repositories;

import com.devsoga.BookStore_V2.enties.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, Integer> {
    Optional<ProductCategoryEntity> findByCategoryCode(String categoryCode);
}
