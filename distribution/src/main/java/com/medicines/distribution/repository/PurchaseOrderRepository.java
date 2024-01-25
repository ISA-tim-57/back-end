package com.medicines.distribution.repository;

import com.medicines.distribution.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder,Integer> {

}
