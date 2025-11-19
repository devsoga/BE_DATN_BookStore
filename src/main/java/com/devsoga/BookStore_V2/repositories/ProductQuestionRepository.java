package com.devsoga.BookStore_V2.repositories;

import com.devsoga.BookStore_V2.enties.ProductQuestionEntity;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductQuestionRepository extends JpaRepository<ProductQuestionEntity, Long> {
    Optional<ProductQuestionEntity> findByQuestionCode(String questionCode);
    List<ProductQuestionEntity> findByProductCode(String productCode);
}
