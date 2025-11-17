package com.devsoga.BookStore_V2.repositories;

import com.devsoga.BookStore_V2.enties.InvoiceDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetailEntity, Integer> {
    Optional<InvoiceDetailEntity> findByOrderDetailCode(String orderDetailCode);
    List<InvoiceDetailEntity> findByOrderEntity_OrderCode(String orderCode);
}
