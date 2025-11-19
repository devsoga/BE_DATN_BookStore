package com.devsoga.BookStore_V2.enties;

import jakarta.persistence.*;

@Entity
@Table(name = "review_image")
public class ReviewImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "review_code", nullable = false)
    private String reviewCode;

    @Column(name = "image_path", nullable = false)
    private String imagePath;

    public ReviewImageEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getReviewCode() { return reviewCode; }
    public void setReviewCode(String reviewCode) { this.reviewCode = reviewCode; }
    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }
}
