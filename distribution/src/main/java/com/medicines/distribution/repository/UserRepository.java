package com.medicines.distribution.repository;


import com.medicines.distribution.model.Company;
import com.medicines.distribution.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByEmail(String email);

    default User changePassword(Integer id, String newPassword){
        User user = findById(id).orElse(null);
        if(user != null){
            user.setPassword(newPassword);
            save(user);

            return user;
        }
        else{
            return null;
        }
    }




}

