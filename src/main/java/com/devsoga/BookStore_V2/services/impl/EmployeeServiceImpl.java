package com.devsoga.BookStore_V2.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsoga.BookStore_V2.enties.EmployeeEntity;
import com.devsoga.BookStore_V2.repositories.EmployeeRepository;
import com.devsoga.BookStore_V2.services.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeEntity create(EmployeeEntity employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public EmployeeEntity update(Integer id, EmployeeEntity employee) {
        EmployeeEntity existing = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        existing.setEmployeeCode(employee.getEmployeeCode());
        existing.setEmployeeName(employee.getEmployeeName());
        existing.setBirthDate(employee.getBirthDate());
        existing.setGender(employee.getGender());
        existing.setAccountEntity(employee.getAccountEntity());

        return employeeRepository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Employee not found with id: " + id);
        }
        employeeRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeEntity getById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeEntity> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeEntity getByCode(String employeeCode) {
        return employeeRepository.findByEmployeeCode(employeeCode)
                .orElseThrow(() -> new RuntimeException("Employee not found with code: " + employeeCode));
    }

    @Override
    @Transactional(readOnly = true)
    public List<EmployeeEntity> searchByName(String nameKeyword) {
        return employeeRepository.findByEmployeeNameContainingIgnoreCase(nameKeyword);
    }
}
