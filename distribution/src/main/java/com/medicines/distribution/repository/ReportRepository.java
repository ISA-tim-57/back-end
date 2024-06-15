package com.medicines.distribution.repository;

import com.medicines.distribution.model.Equipment;
import com.medicines.distribution.model.PurchaseOrder;
import com.medicines.distribution.model.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ReportRepository extends JpaRepository<Report,Integer> {


   // Set<Report> findAllBySenderId(Integer id);
    Set<Report> findAllBySenderUserId(Integer id);
}
