package com.medicines.distribution.repository;

import com.medicines.distribution.model.Company;
import com.medicines.distribution.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment,Integer> {

    default Equipment update(Integer id, Equipment updatedEquipment){
        Equipment equipment = findById(id).orElse(null);

        if(equipment != null){
            equipment.setName(updatedEquipment.getName());
            equipment.setDescription(updatedEquipment.getDescription());
            equipment.setPrice(updatedEquipment.getPrice());
            equipment.setCount(updatedEquipment.getCount());
            save(equipment);
        }

        return equipment;
    }

    default void deleteEquipmentLogical(Integer id){
        Equipment equipment = findById(id).orElse(null);

        if(equipment != null) {
            equipment.setDeleted(true);
            save(equipment);
        }
    }

    List<Equipment> findByNameContainingIgnoreCaseAndCompanyIdAndIsDeleted(String substring, Integer companyId, boolean isDeleted);


}
