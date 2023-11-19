package com.medicines.distribution.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.medicines.distribution.model.User;

public interface UserRepository extends JpaRepository<User,Integer>{



}