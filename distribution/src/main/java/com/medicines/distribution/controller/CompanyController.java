package com.medicines.distribution.controller;

import com.medicines.distribution.dto.CompanyDTO;
import com.medicines.distribution.model.Company;
import com.medicines.distribution.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/copmanies")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping(value = "/all")
    ResponseEntity<List<CompanyDTO>> getAll(){
        List<Company> companies = companyService.findAll();

        List<CompanyDTO> companiesDTO = new ArrayList<>();
        for(Company c : companies){
            companiesDTO.add(new CompanyDTO(c));
        }
        return new ResponseEntity<>(companiesDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CompanyDTO> getCompany(@PathVariable Integer id){
        Company company = companyService.findOne(id);

        if(company == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new CompanyDTO(company),HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<CompanyDTO> saveCompany(@RequestBody CompanyDTO companyDTO) {

        Company company = new Company();
        company.setName(companyDTO.getName());
        company.setDescription(companyDTO.getDescription());
        company.setAddress(companyDTO.convertToAddress(companyDTO.getAddress()));
        company.setRating(companyDTO.getRating());

        company = companyService.save(company);
        return new ResponseEntity<>(new CompanyDTO(company), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Integer id) {

        Company company = companyService.findOne(id);

        if (company != null) {
            companyService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
