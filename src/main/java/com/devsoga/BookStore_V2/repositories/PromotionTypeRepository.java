package com.devsoga.BookStore_V2.repositories;

import com.devsoga.BookStore_V2.enties.PromotionTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromotionTypeRepository extends JpaRepository<PromotionTypeEntity, Integer> {
    Optional<PromotionTypeEntity> findByPromotionTypeCode(String promotionTypeCode);
}
