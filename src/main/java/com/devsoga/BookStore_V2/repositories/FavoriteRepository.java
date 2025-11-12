package com.devsoga.BookStore_V2.repositories;

import com.devsoga.BookStore_V2.enties.FavoriteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Integer> {
    Optional<FavoriteEntity> findByCustomerEntity_CustomerCodeAndProductEntity_ProductCode(String customerCode, String productCode);
    List<FavoriteEntity> findByCustomerEntity_CustomerCode(String customerCode);
    List<FavoriteEntity> findByCustomerEntity_CustomerCodeOrderByCreatedDateDesc(String customerCode);
}
