// File: src/main/java/com/devsoga/BookStore_V2/services/EmployeeService.java
package com.devsoga.BookStore_V2.services;

import java.util.List;

import com.devsoga.BookStore_V2.enties.EmployeeEntity;

public interface EmployeeService {

    EmployeeEntity create(EmployeeEntity employee);

    EmployeeEntity update(Integer id, EmployeeEntity employee);

    void delete(Integer id);

    EmployeeEntity getById(Integer id);

    List<EmployeeEntity> getAll();

    EmployeeEntity getByCode(String employeeCode);

    List<EmployeeEntity> searchByName(String nameKeyword);
}
