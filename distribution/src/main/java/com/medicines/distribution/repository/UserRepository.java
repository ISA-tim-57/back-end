package com.medicines.distribution.repository;

import com.medicines.distribution.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
