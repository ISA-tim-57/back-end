package com.medicines.distribution.service;

import com.medicines.distribution.dto.EquipmentDTO;
import com.medicines.distribution.model.Company;
import com.medicines.distribution.model.Equipment;
import com.medicines.distribution.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EquipmentService {
    
    @Autowired
    EquipmentRepository equipmentRepository;

    @Autowired
    PurchaseOrderService purchaseOrderService;

    public Equipment findOne(Integer id){
        return equipmentRepository.findById(id).orElseGet(null);
    }

    public List<Equipment> findAll(){
        return equipmentRepository.findAll();
    }

    public Equipment save(Equipment equipment){
        return equipmentRepository.save(equipment);
    }

    public void remove(Integer id) {
        equipmentRepository.deleteById(id);
    }

    public EquipmentDTO update(Integer id,EquipmentDTO updatedEquipment){
        Equipment equipment = new Equipment();
        equipment.setName(updatedEquipment.getName());
        equipment.setDescription(updatedEquipment.getDescription());
        equipment.setPrice(updatedEquipment.getPrice());
        equipment.setCount(updatedEquipment.getCount());

        return new EquipmentDTO(equipmentRepository.update(id,equipment));
    }

    public boolean delete(Integer id){
        if(!purchaseOrderService.isEquipmentPartOfOrder(id)){
            equipmentRepository.deleteEquipmentLogical(id);
            return true;
        }
        else{
            return false;
        }
    }

    public List<EquipmentDTO> findByNamePart(String namePart, Integer companyId){
        List<Equipment> equipments=  equipmentRepository.findByNameContainingIgnoreCaseAndCompanyIdAndIsDeleted(namePart.toLowerCase(),companyId, false);

        List<EquipmentDTO> equipmentsDTO = new ArrayList<>();
        for(Equipment e : equipments){
            equipmentsDTO.add(new EquipmentDTO(e));
        }
        return equipmentsDTO;
    }
    
}
