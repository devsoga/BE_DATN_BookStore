package com.devsoga.BookStore_V2.enties;

import jakarta.persistence.*;

@Entity
@Table(name = "product_answer")
public class ProductAnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "answer_code", unique = true, nullable = false)
    private String answerCode;

    @Column(name = "question_code", nullable = false)
    private String questionCode;

    @Column(name = "account_code")
    private String accountCode;

    @Column(name = "responder_type")
    private String responderType = "USER";

    @Column(name = "is_responder_shop")
    private Boolean isResponderShop = false;

    @Column(name = "is_responder_real_buyer")
    private Boolean isResponderRealBuyer = false;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "is_accepted")
    private Boolean isAccepted = false;

    public ProductAnswerEntity() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAnswerCode() { return answerCode; }
    public void setAnswerCode(String answerCode) { this.answerCode = answerCode; }
    public String getQuestionCode() { return questionCode; }
    public void setQuestionCode(String questionCode) { this.questionCode = questionCode; }
    public String getAccountCode() { return accountCode; }
    public void setAccountCode(String accountCode) { this.accountCode = accountCode; }
    public String getResponderType() { return responderType; }
    public void setResponderType(String responderType) { this.responderType = responderType; }
    public Boolean getIsResponderShop() { return isResponderShop; }
    public void setIsResponderShop(Boolean isResponderShop) { this.isResponderShop = isResponderShop; }
    public Boolean getIsResponderRealBuyer() { return isResponderRealBuyer; }
    public void setIsResponderRealBuyer(Boolean isResponderRealBuyer) { this.isResponderRealBuyer = isResponderRealBuyer; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Boolean getIsAccepted() { return isAccepted; }
    public void setIsAccepted(Boolean isAccepted) { this.isAccepted = isAccepted; }
}
