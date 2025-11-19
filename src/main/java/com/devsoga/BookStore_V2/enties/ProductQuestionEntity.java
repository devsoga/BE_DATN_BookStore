package com.devsoga.BookStore_V2.enties;

import jakarta.persistence.*;

@Entity
@Table(name = "product_question")
public class ProductQuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "question_code", unique = true, nullable = false)
    private String questionCode;

    @Column(name = "product_code", nullable = false)
    private String productCode;

    @Column(name = "account_code")
    private String accountCode;

    @Column(name = "asker_name")
    private String askerName;

    @Column(name = "asker_email")
    private String askerEmail;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "status")
    private String status = "OPEN";

    public ProductQuestionEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getQuestionCode() { return questionCode; }
    public void setQuestionCode(String questionCode) { this.questionCode = questionCode; }
    public String getProductCode() { return productCode; }
    public void setProductCode(String productCode) { this.productCode = productCode; }
    public String getAccountCode() { return accountCode; }
    public void setAccountCode(String accountCode) { this.accountCode = accountCode; }
    public String getAskerName() { return askerName; }
    public void setAskerName(String askerName) { this.askerName = askerName; }
    public String getAskerEmail() { return askerEmail; }
    public void setAskerEmail(String askerEmail) { this.askerEmail = askerEmail; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
