package com.devsoga.BookStore_V2.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsoga.BookStore_V2.enties.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    Optional<ProductEntity> findByProductCode(String productCode);
    
    // Search by product name, description or product code (case-insensitive), newest first
    java.util.List<ProductEntity> findByProductNameContainingIgnoreCaseOrDescriptionContainingIgnoreCaseOrProductCodeContainingIgnoreCaseOrderByCreatedDateDesc(
            String productName, String description, String productCode
    );

    @org.springframework.data.jpa.repository.Query(value = "SELECT COUNT(*) FROM product WHERE promotion_code = :code", nativeQuery = true)
    int countByPromotionCode(@org.springframework.data.repository.query.Param("code") String code);

    // Find products by category code
    java.util.List<ProductEntity> findByProductCategoryEntity_CategoryCode(String categoryCode);

    // Find products by category type
    java.util.List<ProductEntity> findByProductCategoryEntity_CategoryType(String categoryType);
}
