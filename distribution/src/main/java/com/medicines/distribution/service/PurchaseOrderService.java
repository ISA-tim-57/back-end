package com.medicines.distribution.service;

import com.medicines.distribution.controller.WebSocketController;
import com.medicines.distribution.model.Appointment;
import com.medicines.distribution.model.PurchaseOrder;
import com.medicines.distribution.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class PurchaseOrderService {

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    WebSocketController webSocketController;

    public Set<PurchaseOrder> getCompanyAdminsOrdersOnHold(Integer CompanyAdminId){
        return purchaseOrderRepository.findAllByCompanyAdminUserIdAndStatus(CompanyAdminId, PurchaseOrder.Status.ON_HOLD);
    }

    @Scheduled(fixedRate = 60000)
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
        webSocketController.broadcastUpdatedOrders(updatedOrders);
    }

    public PurchaseOrder markAsCompleted(Integer id){
        return purchaseOrderRepository.markAsCompleted(id);
    }

}
