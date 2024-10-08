package com.medicines.distribution.controller;

import com.medicines.distribution.dto.*;
import com.medicines.distribution.model.*;
import com.medicines.distribution.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/companies")
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

        CompanyDTO companyDTO = new CompanyDTO(company);
        return new ResponseEntity<>(companyDTO,HttpStatus.OK);
    }

    @GetMapping("/{companyId}/admins")
    public ResponseEntity<List<CompanyAdminDTO>> getCompanyAdmins(@PathVariable Integer companyId){
        Company company = companyService.findOne(companyId);

        if (company != null) {
            Set<CompanyAdmin> admins = company.getCompanyAdmins();
            List<CompanyAdminDTO> adminsDTOS = new ArrayList<>();
            for(CompanyAdmin u : admins){
                adminsDTOS.add(new CompanyAdminDTO(u));
            }
            return new ResponseEntity<>(adminsDTOS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{companyId}/equipments")
    public ResponseEntity<List<EquipmentDTO>> getCompanyEquipments(@PathVariable Integer companyId) {
        Company company = companyService.findOne(companyId);

        if (company != null) {
            Set<Equipment> equipments = company.getEquipments();
            List<EquipmentDTO> equipmentDTOS = new ArrayList<>();
            for(Equipment e : equipments){
                if(!e.isDeleted()){
                    equipmentDTOS.add(new EquipmentDTO(e));
                }
            }
            return new ResponseEntity<>(equipmentDTOS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/{userId}/companies")
    public ResponseEntity<List<CompanyDTO>> getCompanyByUserForReport(@PathVariable Integer userId) {
        List<Company> companies = companyService.getCompanyByUserForReport(userId);

        List<CompanyDTO> companiesDTO = new ArrayList<>();
        for(Company c : companies){
            companiesDTO.add(new CompanyDTO(c));
        }
        return new ResponseEntity<>(companiesDTO, HttpStatus.OK);
    }


    @GetMapping("/{companyId}/appointments")
    public ResponseEntity<List<AppointmentDTO>> getCompanyAppointments(@PathVariable Integer companyId) {
        Company company = companyService.findOne(companyId);

        if (company != null) {
            Set<Appointment> appointments = company.getAppointments();
            List<AppointmentDTO> appointmentDTOS = new ArrayList<>();

            for(Appointment a : appointments){
                if(a.getDateAndTime().isAfter(LocalDateTime.now())) {
                    appointmentDTOS.add(new AppointmentDTO(a));
                }
            }
            return new ResponseEntity<>(appointmentDTOS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<CompanyDTO> saveCompany(@RequestBody CompanyDTO companyDTO) {

        Company company = new Company();
        company.setName(companyDTO.getName());
        company.setDescription(companyDTO.getDescription());
        company.setAddress(companyDTO.convertToAddress(companyDTO.getAddress()));
        company.setRating(companyDTO.getRating());
        company.setWorkingHoursStart(companyDTO.getWorkingHoursStart());
        company.setWorkingHoursEnd(companyDTO.getWorkingHoursEnd());

        company = companyService.save(company);
        return new ResponseEntity<>(new CompanyDTO(company), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDTO> update(@PathVariable Integer id, @RequestBody Company updatedCompany){

        Company company = companyService.update(id,updatedCompany);
        return new ResponseEntity<>(new CompanyDTO(company), HttpStatus.OK);
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
