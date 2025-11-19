package com.devsoga.BookStore_V2.enties;

import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class ReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "review_code", unique = true, nullable = false)
    private String reviewCode;

    @Column(name = "product_code", nullable = false)
    private String productCode;

    @Column(name = "customer_code", nullable = false)
    private String customerCode;

    @Column(name = "rating", nullable = false)
    private Integer rating;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "is_verified_purchase")
    private Boolean isVerifiedPurchase = false;

    @Column(name = "status")
    private String status = "PUBLISHED";

    public ReviewEntity() {}

    // getters / setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getReviewCode() { return reviewCode; }
    public void setReviewCode(String reviewCode) { this.reviewCode = reviewCode; }
    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }
    public String getCustomerCode() { return customerCode; }
    public void setCustomerCode(String customerCode) { this.customerCode = customerCode; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Boolean getIsVerifiedPurchase() { return isVerifiedPurchase; }
    public void setIsVerifiedPurchase(Boolean isVerifiedPurchase) { this.isVerifiedPurchase = isVerifiedPurchase; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
