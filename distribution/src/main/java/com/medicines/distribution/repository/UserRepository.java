package com.medicines.distribution.repository;

import com.medicines.distribution.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    default User updateUser(Integer id, User newUser){
        User user = findById(id).orElse(null);

        if(user != null){
            user.setName(newUser.getName());
            user.setSurname(newUser.getSurname());
            save(user);
        }
        return user;
    }
}
