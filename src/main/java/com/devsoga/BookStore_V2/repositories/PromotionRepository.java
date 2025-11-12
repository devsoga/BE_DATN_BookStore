package com.devsoga.BookStore_V2.repositories;

import com.devsoga.BookStore_V2.enties.PromotionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<PromotionEntity, Integer> {
    Optional<PromotionEntity> findByPromotionCode(String promotionCode);
}
