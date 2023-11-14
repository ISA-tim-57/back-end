package com.medicines.distribution.repository;

import com.medicines.distribution.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipmentRepository extends JpaRepository<Equipment,Integer> {
}
