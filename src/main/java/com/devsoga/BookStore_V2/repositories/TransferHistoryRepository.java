package com.devsoga.BookStore_V2.repositories;

import com.devsoga.BookStore_V2.enties.TransferHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransferHistoryRepository extends JpaRepository<TransferHistoryEntity, Long> {
    List<TransferHistoryEntity> findByOrderCode(String orderCode);
}