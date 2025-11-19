package com.devsoga.BookStore_V2.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsoga.BookStore_V2.enties.CustomerEntity;
import com.devsoga.BookStore_V2.repositories.CustomerRepository;
import com.devsoga.BookStore_V2.services.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerEntity create(CustomerEntity customer) {
        if (customer.getPoints() == null) {
            customer.setPoints(0.0);
        }
        return customerRepository.save(customer);
    }

    @Override
    public CustomerEntity update(Integer id, CustomerEntity customer) {
        CustomerEntity existing = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));

        existing.setCustomerCode(customer.getCustomerCode());
        existing.setCustomerName(customer.getCustomerName());
        existing.setAddress(customer.getAddress());
        existing.setPoints(customer.getPoints());
        existing.setCustomerTypeEntity(customer.getCustomerTypeEntity());
        existing.setAccountEntity(customer.getAccountEntity());

        return customerRepository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        if (!customerRepository.existsById(id)) {
            throw new RuntimeException("Customer not found with id: " + id);
        }
        customerRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerEntity getById(Integer id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerEntity> getAll() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerEntity getByCode(String customerCode) {
        return customerRepository.findByCustomerCode(customerCode)
                .orElseThrow(() -> new RuntimeException("Customer not found with code: " + customerCode));
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerEntity> searchByName(String nameKeyword) {
        return customerRepository.findByCustomerNameContainingIgnoreCase(nameKeyword);
    }
}
