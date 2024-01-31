package com.medicines.distribution.service;

import com.medicines.distribution.controller.WebSocketController;
import com.medicines.distribution.model.Appointment;
import com.medicines.distribution.model.BasicUser;
import com.medicines.distribution.model.Equipment;
import com.medicines.distribution.model.PurchaseOrder;
import com.medicines.distribution.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class PurchaseOrderService {

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    WebSocketController webSocketController;

    public Set<PurchaseOrder> getCompanyAdminsOrdersOnHold(Integer CompanyAdminId){
        return purchaseOrderRepository.findAllByCompanyAdminUserIdAndStatus(CompanyAdminId, PurchaseOrder.Status.ON_HOLD);
    }

    public Set<PurchaseOrder> getPurchasedOrdersByEquipment(Integer equipmentId){
        return purchaseOrderRepository.findAllByOrderEquipmentsEquipmentIdAndStatus(equipmentId, PurchaseOrder.Status.ON_HOLD);
    }

    @Scheduled(fixedRate = 60000)
    @Transactional(readOnly = false)
    public void updateOrderStatus(){
        Set<PurchaseOrder> orders = purchaseOrderRepository.findByStatus(PurchaseOrder.Status.ON_HOLD);
        LocalDateTime currentDateTime = LocalDateTime.now();

        for(PurchaseOrder order : orders){
            LocalDateTime appointmentDateTime = order.getAppointment().getDateAndTime();
            Integer duration = order.getAppointment().getDuration();
            if(currentDateTime.isAfter(appointmentDateTime.plusMinutes(duration))){
                order.setStatus(PurchaseOrder.Status.CANCELLED);
                purchaseOrderRepository.save(order);
            }
        }
        Set<PurchaseOrder> updatedOrders = purchaseOrderRepository.findByStatus(PurchaseOrder.Status.ON_HOLD);
    }

    @Transactional(readOnly = false)
    public PurchaseOrder markAsCompleted(Integer id){
        return purchaseOrderRepository.markAsCompleted(id);
    }

    public boolean isEquipmentPartOfOrder(Integer equipmentId){
        Set<PurchaseOrder> orders = purchaseOrderRepository.findAllByOrderEquipmentsEquipmentIdAndStatus(equipmentId, PurchaseOrder.Status.ON_HOLD);
        return !orders.isEmpty();
    }

    public Set<BasicUser> GetAllCustomersForActiveCompanyOrders(Integer companyId){
        Set<PurchaseOrder> orders = purchaseOrderRepository.findAllByCompanyAdminCompanyIdAndStatus(companyId, PurchaseOrder.Status.ON_HOLD);
        Set<BasicUser> customers = new HashSet<>();
        for(PurchaseOrder order : orders){
            customers.add(order.getCustomer());
        }
        return customers;
    }

}
