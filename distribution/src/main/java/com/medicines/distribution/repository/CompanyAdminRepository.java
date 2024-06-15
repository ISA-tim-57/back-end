package com.medicines.distribution.repository;

import com.medicines.distribution.model.CompanyAdmin;
import com.medicines.distribution.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyAdminRepository extends JpaRepository<CompanyAdmin,Integer> {

    CompanyAdmin findByUserId(Integer id);

    CompanyAdmin findAllById(Integer id);



    default CompanyAdmin updateCompanyAdmin(Integer id, CompanyAdmin updatedCompanyAdmin){
        CompanyAdmin user = findByUserId(id);

        if(user != null){
            user.setName(updatedCompanyAdmin.getName());
            user.setSurname(updatedCompanyAdmin.getSurname());

            save(user);
        }
        return user;
    }
}
