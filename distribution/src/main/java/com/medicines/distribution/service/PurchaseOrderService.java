package com.medicines.distribution.service;

import com.medicines.distribution.controller.WebSocketController;
import com.medicines.distribution.dto.OrderEquipmentDTO;
import com.medicines.distribution.dto.PurchaseOrderDTO;
import com.medicines.distribution.model.*;
import com.medicines.distribution.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class PurchaseOrderService {

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;
    @Autowired
    WebSocketController webSocketController;

    @Autowired
    UserService userService;

    @Autowired
    EquipmentRepository equipmentRepository;

    @Autowired
    OrderEquipmentRepository    orderEquipmentRepository;

    @Autowired
    CompanyAdminRepository companyAdminRepository;

    @Autowired
    BasicUserRepository basicUserRepository;

    @Autowired
    Appointmentrepository appointmentRepository;


    public Set<PurchaseOrder> getCompanyAdminsOrdersOnHold(Integer CompanyAdminId) {
        return purchaseOrderRepository.findAllByCompanyAdminUserIdAndStatus(CompanyAdminId, PurchaseOrder.Status.ON_HOLD);
    }

    public Set<PurchaseOrder> getPurchasedOrdersByEquipment(Integer equipmentId) {
        return purchaseOrderRepository.findAllByOrderEquipmentsEquipmentIdAndStatus(equipmentId, PurchaseOrder.Status.ON_HOLD);
    }

    @Scheduled(fixedRate = 60000)
    @Transactional(readOnly = false)
    public void updateOrderStatus() {
        Set<PurchaseOrder> orders = purchaseOrderRepository.findByStatus(PurchaseOrder.Status.ON_HOLD);
        LocalDateTime currentDateTime = LocalDateTime.now();

        for (PurchaseOrder order : orders) {
            LocalDateTime appointmentDateTime = order.getAppointment().getDateAndTime();
            Integer duration = order.getAppointment().getDuration();
            if (currentDateTime.isAfter(appointmentDateTime.plusMinutes(duration))) {
                order.setStatus(PurchaseOrder.Status.CANCELLED);
                BasicUser customer = order.getCustomer();
                customer.setPenalty(customer.getPenalty() + 2);
                userService.updateBasicUser(customer.getId(), customer);
                List<OrderEquipment> orderEquipments = order.getOrderEquipments();
                List<Equipment> allEquipments = new ArrayList<>();
                for (OrderEquipment oreq : orderEquipments) {
                    Equipment tempEquipment = oreq.getEquipment();
                    tempEquipment.setCount(tempEquipment.getCount() + oreq.getQuantity());
                }
                for (Equipment equipment : allEquipments) {
                    equipmentRepository.update(equipment.getId(), equipment);
                }
                purchaseOrderRepository.save(order);
            }
        }
        Set<PurchaseOrder> updatedOrders = purchaseOrderRepository.findByStatus(PurchaseOrder.Status.ON_HOLD);
    }

    @Transactional(readOnly = false)
    public PurchaseOrder markAsCompleted(Integer id) {
        return purchaseOrderRepository.markAsCompleted(id);
    }

    public boolean isEquipmentPartOfOrder(Integer equipmentId) {
        Set<PurchaseOrder> orders = purchaseOrderRepository.findAllByOrderEquipmentsEquipmentIdAndStatus(equipmentId, PurchaseOrder.Status.ON_HOLD);
        return !orders.isEmpty();
    }

    public Set<BasicUser> GetAllCustomersForActiveCompanyOrders(Integer companyId) {
        Set<PurchaseOrder> orders = purchaseOrderRepository.findAllByCompanyAdminCompanyIdAndStatus(companyId, PurchaseOrder.Status.ON_HOLD);
        Set<BasicUser> customers = new HashSet<>();
        for (PurchaseOrder order : orders) {
            customers.add(order.getCustomer());
        }
        return customers;
    }

    public Set<PurchaseOrder> getPurchaseOrdersByUserId(Integer userId) {
        return purchaseOrderRepository.findAllByCustomerUserId(userId);
    }

    @Transactional(readOnly = false)
    public PurchaseOrder cancelByUser(Integer userId, PurchaseOrder purchaseOrder) {
        PurchaseOrder existingOrder = purchaseOrderRepository.findById(purchaseOrder.getId())
                .orElseThrow(() -> new IllegalArgumentException("PurchaseOrder not found"));

        if (existingOrder.getCustomer().getId().equals(userId)) {
            existingOrder.setStatus(PurchaseOrder.Status.CANCELLED);
            purchaseOrderRepository.save(existingOrder);
            return existingOrder;
        } else {
            throw new IllegalArgumentException("User is not authorized to cancel this order");
        }
    }


    @Transactional(readOnly = false)
    public PurchaseOrder addPurchaseOrder(PurchaseOrder purchaseOrder) {
        // Fetch existing entities from the database



        // Set the OrderEquipments to the PurchaseOrder

        // Persist the PurchaseOrder
        return purchaseOrderRepository.save(purchaseOrder);
    }



}
