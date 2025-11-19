package com.devsoga.BookStore_V2.repositories;

import com.devsoga.BookStore_V2.enties.ReviewEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<ReviewEntity, Long> {
    Optional<ReviewEntity> findByReviewCode(String reviewCode);
    List<ReviewEntity> findByProductCode(String productCode);
    Optional<ReviewEntity> findByProductCodeAndCustomerCode(String productCode, String customerCode);
}
