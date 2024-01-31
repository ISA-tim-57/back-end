package com.medicines.distribution.controller;

import com.medicines.distribution.dto.CompanyDTO;
import com.medicines.distribution.dto.EquipmentDTO;
import com.medicines.distribution.model.Company;
import com.medicines.distribution.model.Equipment;
import com.medicines.distribution.service.CompanyService;
import com.medicines.distribution.service.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/equipments")
public class EquipmentController {

    @Autowired
    EquipmentService equipmentService;

    @Autowired
    CompanyService companyService;


    @GetMapping(value = "/all")
    ResponseEntity<List<EquipmentDTO>> getAll(){
        List<Equipment> equipments = equipmentService.findAll();

        List<EquipmentDTO> equipmentsDTO = new ArrayList<>();
        for(Equipment e : equipments){
            equipmentsDTO.add(new EquipmentDTO(e));
        }
        return new ResponseEntity<>(equipmentsDTO, HttpStatus.OK);
    }



    @PostMapping(consumes = "application/json")
    public ResponseEntity<EquipmentDTO> saveEquipment(@RequestBody EquipmentDTO equipmentDTO){

        Equipment equipment = new Equipment();
        equipment.setName(equipmentDTO.getName());
        equipment.setDescription(equipmentDTO.getDescription());
        equipment.setPrice(equipmentDTO.getPrice());
        equipment.setCount(equipmentDTO.getCount());
        equipment.setCompany(companyService.findOne(equipmentDTO.getCompanyId()));
        equipment.setDeleted(false);

        equipment = equipmentService.save(equipment);
        return new ResponseEntity<>(new EquipmentDTO(equipment), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EquipmentDTO> update(@PathVariable Integer id, @RequestBody EquipmentDTO updatedEquipment){
        EquipmentDTO equipment = equipmentService.update(id,updatedEquipment);
        return new ResponseEntity<>(equipment, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        return new ResponseEntity<Boolean>(equipmentService.delete(id),HttpStatus.OK);
    }

    @GetMapping("/search/{companyId}/{namePart}")
    public ResponseEntity<List<EquipmentDTO>> search(@PathVariable String namePart, @PathVariable Integer companyId){
        return new ResponseEntity<>(equipmentService.findByNamePart(namePart,companyId),HttpStatus.OK);
    }
}
