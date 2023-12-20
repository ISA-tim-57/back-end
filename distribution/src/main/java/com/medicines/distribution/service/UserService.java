package com.medicines.distribution.service;

import com.medicines.distribution.dto.ChangePasswordRequest;
import com.medicines.distribution.model.Company;
import com.medicines.distribution.model.User;
import com.medicines.distribution.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressService addressService;
    @Autowired
    PasswordEncoder passwordEncoder;



    public User save(User user){
        return userRepository.save(user);
    }


    public List<User> findAll(){
        return userRepository.findAll();
    }


    public User findOne(Integer id){
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));
    }


    public User findByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email);
    }

    public User changePassword(Integer id, ChangePasswordRequest request){
        User user = userRepository.findById(id).orElse(null);
        if(user != null && passwordEncoder.matches(request.getOldPassword(), user.getPassword())){
            return userRepository.changePassword(id,passwordEncoder.encode(request.getNewPassword()));
        }
        else{
            return null;
        }


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


