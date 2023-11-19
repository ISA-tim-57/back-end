package com.medicines.distribution.service;

import com.medicines.distribution.model.Company;
import com.medicines.distribution.model.User;
import com.medicines.distribution.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserService{

    @Autowired
    private UserRepository userRepository;

    public User save(User user){
        return userRepository.save(user);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }







}