package com.devsoga.BookStore_V2.services;

import com.devsoga.BookStore_V2.enties.ReviewEntity;
import com.devsoga.BookStore_V2.repositories.ReviewRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewEntity create(ReviewEntity r) {
        return reviewRepository.save(r);
    }

    public Optional<ReviewEntity> findByReviewCode(String code) {
        return reviewRepository.findByReviewCode(code);
    }

    public List<ReviewEntity> findByProductCode(String productCode) {
        return reviewRepository.findByProductCode(productCode);
    }

    public Optional<ReviewEntity> update(String reviewCode, ReviewEntity updated) {
        Optional<ReviewEntity> exist = reviewRepository.findByReviewCode(reviewCode);
        if (exist.isEmpty()) return Optional.empty();
        ReviewEntity r = exist.get();
        r.setRating(updated.getRating());
        r.setContent(updated.getContent());
        r.setIsVerifiedPurchase(updated.getIsVerifiedPurchase());
        r.setStatus(updated.getStatus());
        reviewRepository.save(r);
        return Optional.of(r);
    }

    public void deleteByReviewCode(String reviewCode) {
        reviewRepository.findByReviewCode(reviewCode).ifPresent(r -> reviewRepository.delete(r));
    }
}
