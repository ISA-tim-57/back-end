package com.medicines.distribution.controller;

import com.medicines.distribution.dto.EquipmentDTO;
import com.medicines.distribution.model.Company;
import com.medicines.distribution.model.Equipment;
import com.medicines.distribution.service.CompanyService;
import com.medicines.distribution.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/equipments")
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;

    @Autowired
    CompanyService companyService;


    @PostMapping(consumes = "application/json")
    public ResponseEntity<EquipmentDTO> saveEquipment(@RequestBody EquipmentDTO equipmentDTO){

        Equipment equipment = new Equipment();
        equipment.setName(equipmentDTO.getName());
        equipment.setDescription(equipmentDTO.getDescription());
        equipment.setPrice(equipmentDTO.getPrice());
        equipment.setCompany(companyService.findOne(equipmentDTO.getCompanyId()));

        equipment = equipmentService.save(equipment);
        return new ResponseEntity<>(new EquipmentDTO(equipment), HttpStatus.CREATED);
    }
}
