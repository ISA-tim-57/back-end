package com.medicines.distribution.repository;


import com.medicines.distribution.model.Company;
import com.medicines.distribution.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByEmail(String email);
    default User updateUser(Integer id, User newUser){
        User user = findById(id).orElse(null);

        if(user != null){
            user.setName(newUser.getName());
            user.setSurname(newUser.getSurname());
            save(user);
        }
        return user;
    }



    default User updateCompanyAdmin(Integer id, User updatedCompanyAdmin){
        User user = findById(id).orElse(null);

        if(user != null){
            user.setName(updatedCompanyAdmin.getName());
            user.setSurname(updatedCompanyAdmin.getSurname());
            user.setPhone(updatedCompanyAdmin.getPhone());

            save(user);
        }
        return user;
    }
}

