package com.devsoga.BookStore_V2.repositories;

import com.devsoga.BookStore_V2.enties.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepository extends JpaRepository<CouponEntity, Integer> {
    Optional<CouponEntity> findByCouponCode(String couponCode);
}
