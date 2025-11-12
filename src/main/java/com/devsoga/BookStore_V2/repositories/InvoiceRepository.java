package com.devsoga.BookStore_V2.repositories;

import com.devsoga.BookStore_V2.enties.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Integer> {
    Optional<InvoiceEntity> findByOrderCode(String orderCode);
}
