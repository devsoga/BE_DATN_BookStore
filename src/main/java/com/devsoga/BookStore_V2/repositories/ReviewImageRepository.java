package com.devsoga.BookStore_V2.repositories;

import com.devsoga.BookStore_V2.enties.ReviewImageEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewImageRepository extends JpaRepository<ReviewImageEntity, Long> {
    List<ReviewImageEntity> findByReviewCode(String reviewCode);
}
