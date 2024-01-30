package com.medicines.distribution.service;

import com.medicines.distribution.model.Company;
import com.medicines.distribution.model.PurchaseOrder;
import com.medicines.distribution.repository.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AddressService addressService;


    public Company findOne(Integer id){
            return companyRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Company not found with id: " + id));
    }

    public List<Company> findAll(){
        return companyRepository.findAll();
    }

    @Transactional(readOnly = false)
    public Company save(Company company){
        return companyRepository.save(company);
    }


    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    public void remove(Integer id) {
        companyRepository.deleteById(id);
    }

    @Transactional(readOnly = false, isolation = Isolation.REPEATABLE_READ)
    public Company update(Integer id,Company company) {
        company.setAddress(addressService.update(company.getAddress().getId(),company.getAddress()));
        return companyRepository.update(id,company);
    }
}
