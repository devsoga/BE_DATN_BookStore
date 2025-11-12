package com.devsoga.BookStore_V2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devsoga.BookStore_V2.enties.EmployeeEntity;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    Optional<EmployeeEntity> findByEmployeeCode(String employeeCode);
}
