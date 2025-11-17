package com.devsoga.BookStore_V2.repositories;

import com.devsoga.BookStore_V2.enties.CartEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {
    Optional<CartEntity> findByCustomerEntity_CustomerCodeAndProductEntity_ProductCode(String customerCode, String productCode);
    List<CartEntity> findByCustomerEntity_CustomerCode(String customerCode);
    List<CartEntity> findByProductEntity_ProductCode(String productCode);

    @Modifying
    @Transactional
    @Query("UPDATE cart c SET c.promotionCode = :promo WHERE c.productEntity.productCode = :productCode")
    int updatePromotionCodeByProductCode(@Param("productCode") String productCode, @Param("promo") String promo);
}
