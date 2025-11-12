package com.devsoga.BookStore_V2.repositories;

import com.devsoga.BookStore_V2.enties.PromotionOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionOrderRepository extends JpaRepository<PromotionOrderEntity, Integer> {
	java.util.List<PromotionOrderEntity> findByOrderEntity_OrderCode(String orderCode);

}
