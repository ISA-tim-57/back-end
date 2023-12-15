package com.medicines.distribution.service;

import com.medicines.distribution.model.Role;
import com.medicines.distribution.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Role findById(Long id) {
        Role auth = this.roleRepository.getRoleById(id);
        return auth;
    }

    public List<Role> findByName(String name) {
        List<Role> roles = this.roleRepository.findByName(name);
        return roles;
    }
}
