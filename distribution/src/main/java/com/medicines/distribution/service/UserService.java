package com.medicines.distribution.service;

import com.medicines.distribution.model.Company;
import com.medicines.distribution.model.User;
import com.medicines.distribution.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressService addressService;



    public User save(User user){
        return userRepository.save(user);
    }


    public List<User> findAll(){
        return userRepository.findAll();
    }


    public User findOne(Integer id){
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));
    }


    public User updateUser(Integer id, User user) {
        user.setAddress(addressService.update(user.getAddress().getId(),user.getAddress()));
        return userRepository.updateUser(id, user);
    }


    public User updateCompanyAdmin(Integer id, User user) {
        user.setAddress(addressService.update(user.getAddress().getId(),user.getAddress()));
        return userRepository.updateCompanyAdmin(id,user);
    }

}


