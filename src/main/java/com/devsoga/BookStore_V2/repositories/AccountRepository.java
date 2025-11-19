package com.devsoga.BookStore_V2.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsoga.BookStore_V2.enties.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
    
    List<AccountEntity> findByUsername(String username);
    Optional<AccountEntity> findByAccountCode(String accountCode);
    Optional<AccountEntity> findByEmail(String email);

}
