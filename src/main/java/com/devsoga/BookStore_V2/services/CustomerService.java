package com.devsoga.BookStore_V2.services;

import java.util.List;

import com.devsoga.BookStore_V2.enties.CustomerEntity;

public interface CustomerService {

    // Thêm mới
    CustomerEntity create(CustomerEntity customer);

    // Cập nhật theo id
    CustomerEntity update(Integer id, CustomerEntity customer);

    // Xóa theo id
    void delete(Integer id);

    // Lấy 1 customer theo id
    CustomerEntity getById(Integer id);

    // Lấy tất cả
    List<CustomerEntity> getAll();

    // Tìm 1 customer theo customer_code
    CustomerEntity getByCode(String customerCode);

    // Tìm danh sách theo tên
    List<CustomerEntity> searchByName(String nameKeyword);
}
