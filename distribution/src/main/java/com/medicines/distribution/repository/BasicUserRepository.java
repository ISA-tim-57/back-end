package com.medicines.distribution.repository;

import com.medicines.distribution.model.BasicUser;
import com.medicines.distribution.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicUserRepository extends JpaRepository<BasicUser,Integer> {

    BasicUser findByUserId(Integer userId);
    default BasicUser updateBasicUser(Integer id, BasicUser newBasicUser){
        BasicUser user = findById(id).orElse(null);

        if(user != null){
            user.setName(newBasicUser.getName());
            user.setSurname(newBasicUser.getSurname());
            user.setPenalty(newBasicUser.getPenalty());
            save(user);
        }
        return user;
    }
}
