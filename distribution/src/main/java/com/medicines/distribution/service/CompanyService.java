package com.medicines.distribution.service;

import com.medicines.distribution.model.Company;
import com.medicines.distribution.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company findOne(Integer id){
            return companyRepository.findById(id).orElseGet(null);
    }

    public List<Company> findAll(){
        return companyRepository.findAll();
    }

    public Company save(Company company){
        return companyRepository.save(company);
    }

    public void remove(Integer id) {
        companyRepository.deleteById(id);
    }
}
