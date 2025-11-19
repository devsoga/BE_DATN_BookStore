package com.devsoga.BookStore_V2.controllers;

import com.devsoga.BookStore_V2.enties.ReviewEntity;
import com.devsoga.BookStore_V2.services.ReviewService;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewEntity> create(@RequestBody ReviewEntity req) {
        ReviewEntity created = reviewService.create(req);
        return ResponseEntity.created(URI.create("/v1/api/reviews/" + created.getReviewCode())).body(created);
    }

    @GetMapping("/{reviewCode}")
    public ResponseEntity<ReviewEntity> get(@PathVariable String reviewCode) {
        Optional<ReviewEntity> r = reviewService.findByReviewCode(reviewCode);
        return r.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/product/{productCode}")
    public ResponseEntity<List<ReviewEntity>> listByProduct(@PathVariable String productCode) {
        return ResponseEntity.ok(reviewService.findByProductCode(productCode));
    }

    @PutMapping("/{reviewCode}")
    public ResponseEntity<ReviewEntity> update(@PathVariable String reviewCode, @RequestBody ReviewEntity req) {
        Optional<ReviewEntity> updated = reviewService.update(reviewCode, req);
        return updated.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{reviewCode}")
    public ResponseEntity<Void> delete(@PathVariable String reviewCode) {
        reviewService.deleteByReviewCode(reviewCode);
        return ResponseEntity.noContent().build();
    }
}
