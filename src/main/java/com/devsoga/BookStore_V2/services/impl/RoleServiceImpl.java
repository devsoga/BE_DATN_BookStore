// File: src/main/java/com/devsoga/BookStore_V2/services/impl/RoleServiceImpl.java
package com.devsoga.BookStore_V2.services.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsoga.BookStore_V2.enties.RoleEntity;
import com.devsoga.BookStore_V2.repositories.RoleRepository;
import com.devsoga.BookStore_V2.services.RoleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleEntity create(RoleEntity role) {
        return roleRepository.save(role);
    }

    @Override
    public RoleEntity update(Integer id, RoleEntity role) {
        RoleEntity existing = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));

        existing.setRoleCode(role.getRoleCode());
        existing.setRoleName(role.getRoleName());

        return roleRepository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        if (!roleRepository.existsById(id)) {
            throw new RuntimeException("Role not found with id: " + id);
        }
        roleRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public RoleEntity getById(Integer id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Role not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<RoleEntity> getAll() {
        return roleRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public RoleEntity getByCode(String roleCode) {
        return roleRepository.findByRoleCode(roleCode)
                .orElseThrow(() -> new RuntimeException("Role not found with code: " + roleCode));
    }

    @Override
    @Transactional(readOnly = true)
    public RoleEntity getByName(String roleName) {
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found with name: " + roleName));
    }
}
