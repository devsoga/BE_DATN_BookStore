package com.devsoga.BookStore_V2.repositories;

import com.devsoga.BookStore_V2.enties.ProductAnswerEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAnswerRepository extends JpaRepository<ProductAnswerEntity, Long> {
    Optional<ProductAnswerEntity> findByAnswerCode(String answerCode);
    List<ProductAnswerEntity> findByQuestionCode(String questionCode);
}
