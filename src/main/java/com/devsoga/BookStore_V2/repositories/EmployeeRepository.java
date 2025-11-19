// File: src/main/java/com/devsoga/BookStore_V2/repositories/EmployeeRepository.java
package com.devsoga.BookStore_V2.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsoga.BookStore_V2.enties.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    Optional<EmployeeEntity> findByEmployeeCode(String employeeCode);

    List<EmployeeEntity> findByEmployeeNameContainingIgnoreCase(String employeeName);
}
