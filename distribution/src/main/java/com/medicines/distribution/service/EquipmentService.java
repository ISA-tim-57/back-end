package com.medicines.distribution.service;

import com.medicines.distribution.model.Company;
import com.medicines.distribution.model.Equipment;
import com.medicines.distribution.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {
    
    @Autowired
    EquipmentRepository equipmentRepository;

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
    
}
