package com.medicines.distribution.repository;

import com.medicines.distribution.model.Appointment;
import com.medicines.distribution.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder,Integer> {

    Set<PurchaseOrder> findAllByCompanyAdminUserIdAndStatus(Integer userId, PurchaseOrder.Status status);
    Set<PurchaseOrder> findByStatus(PurchaseOrder.Status status);

    Set<PurchaseOrder> findAllByCompanyAdminCompanyIdAndStatus(Integer companyId, PurchaseOrder.Status status);

    Set<PurchaseOrder> findAllByOrderEquipmentsEquipmentIdAndStatus(Integer id, PurchaseOrder.Status status);

    Optional<PurchaseOrder> findByAppointmentId(Integer id);

    Set<PurchaseOrder> findAllByCustomerUserId(Integer id);

    default PurchaseOrder markAsCompleted(Integer id){
        Optional<PurchaseOrder> purchaseOrder = findById(id);

        if(purchaseOrder.isPresent()){
            purchaseOrder.get().setStatus(PurchaseOrder.Status.COMPLETED);

            save(purchaseOrder.get());
            return purchaseOrder.get();
        }
        else{
            throw new RuntimeException("Purchase Order not found for ID: " + id);
        }

    }
}
