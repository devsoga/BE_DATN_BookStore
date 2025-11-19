package com.devsoga.BookStore_V2.services;

import java.util.List;

import com.devsoga.BookStore_V2.enties.RoleEntity;

public interface RoleService {

    RoleEntity create(RoleEntity role);

    RoleEntity update(Integer id, RoleEntity role);

    void delete(Integer id);

    RoleEntity getById(Integer id);

    List<RoleEntity> getAll();

    RoleEntity getByCode(String roleCode);

    RoleEntity getByName(String roleName);
}
