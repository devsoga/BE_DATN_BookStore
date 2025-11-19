package com.devsoga.BookStore_V2.services;

import com.devsoga.BookStore_V2.enties.ProductQuestionEntity;
import com.devsoga.BookStore_V2.enties.ProductAnswerEntity;
import com.devsoga.BookStore_V2.repositories.ProductQuestionRepository;
import com.devsoga.BookStore_V2.repositories.ProductAnswerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QAService {

    @Autowired
    private ProductQuestionRepository questionRepository;

    @Autowired
    private ProductAnswerRepository answerRepository;

    public ProductQuestionEntity createQuestion(ProductQuestionEntity q) {
        return questionRepository.save(q);
    }

    public Optional<ProductQuestionEntity> findQuestionByCode(String code) {
        return questionRepository.findByQuestionCode(code);
    }

    public List<ProductQuestionEntity> findQuestionsByProduct(String productCode) {
        return questionRepository.findByProductCode(productCode);
    }

    public ProductAnswerEntity createAnswer(ProductAnswerEntity a) {
        return answerRepository.save(a);
    }

    public List<ProductAnswerEntity> findAnswersByQuestion(String questionCode) {
        return answerRepository.findByQuestionCode(questionCode);
    }

    public Optional<ProductAnswerEntity> findAnswerByCode(String code) {
        return answerRepository.findByAnswerCode(code);
    }

    public void deleteQuestion(String questionCode) {
        questionRepository.findByQuestionCode(questionCode).ifPresent(q -> questionRepository.delete(q));
    }

    public void deleteAnswer(String answerCode) {
        answerRepository.findByAnswerCode(answerCode).ifPresent(a -> answerRepository.delete(a));
    }
}
