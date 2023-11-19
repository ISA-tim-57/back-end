package com.medicines.distribution.repository;

import com.medicines.distribution.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company,Integer> {

    default Company update(Integer id,Company updatedCompany){
        Company company = findById(id).orElse(null);

        if(company != null){
            company.setName(updatedCompany.getName());
            company.setDescription(updatedCompany.getDescription());
            save(company);
        }

        return company;
    }

}
