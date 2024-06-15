package com.medicines.distribution.controller;

import com.medicines.distribution.dto.CompanyAdminDTO;
import com.medicines.distribution.dto.CompanyDTO;
import com.medicines.distribution.model.Company;
import com.medicines.distribution.model.CompanyAdmin;
import com.medicines.distribution.service.CompanyAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/companyAdmins")
public class CompanyAdminController {


    @Autowired
    CompanyAdminService companyAdminService;


    @GetMapping("/{userId}")
    public ResponseEntity<List<CompanyAdminDTO>> getCompanyAdminByUserForReport(@PathVariable Integer userId) {
        List<CompanyAdmin> companieAdminss = companyAdminService.getCompanyByUserForReport(userId);

        List<CompanyAdminDTO> companieAdminsDTO = new ArrayList<>();
        for(CompanyAdmin c : companieAdminss){
            companieAdminsDTO.add(new CompanyAdminDTO(c));
        }
        return new ResponseEntity<>(companieAdminsDTO, HttpStatus.OK);
    }
}
