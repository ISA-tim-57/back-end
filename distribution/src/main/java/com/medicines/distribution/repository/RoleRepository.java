package com.medicines.distribution.repository;

import com.medicines.distribution.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {
    List<Role> findByName(String name);
    Role getRoleById(long id);
}
