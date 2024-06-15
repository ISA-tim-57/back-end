package com.medicines.distribution.service;

import com.medicines.distribution.controller.WebSocketController;
import com.medicines.distribution.dto.OrderEquipmentDTO;
import com.medicines.distribution.dto.PurchaseOrderDTO;
import com.medicines.distribution.model.*;
import com.medicines.distribution.repository.*;
import jakarta.persistence.EntityNotFoundException;
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
    CompanyRepository companyRepository;

    @Autowired
    EmailService emailService;

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
        BasicUser basicUser=basicUserRepository.findByUserId(userId);

        if (basicUser==null) {
            throw new IllegalArgumentException("User is not in database");
        }


        if (!existingOrder.getCustomer().getUser().getId().equals(userId)) {
            throw new IllegalArgumentException("User is not authorized to cancel this order");
        }

        Appointment appointment = existingOrder.getAppointment();
        if (appointment != null) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime appointmentTime = appointment.getDateAndTime();
            appointment.setFree(true);
            appointmentRepository.save(appointment);
            if (appointmentTime.isBefore(now.plusHours(24))) {
               //dva penala
                Integer penali=basicUser.getPenalty();
                penali=penali+2;
                basicUser.setPenalty(penali);
                basicUserRepository.save(basicUser);

            }
            else
            {
                //jedan penal
                Integer penali=basicUser.getPenalty();
                penali=penali+1;
                basicUser.setPenalty(penali);
                basicUserRepository.save(basicUser);
            }
        }

        existingOrder.setStatus(PurchaseOrder.Status.CANCELLED);
        purchaseOrderRepository.save(existingOrder);
        return existingOrder;
    }

   @Transactional(readOnly = false)
    public PurchaseOrder addPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {
        // Here you can fetch and set existing entities if needed

       CompanyAdmin companyAdmin = companyAdminRepository.findByUserId(purchaseOrderDTO.getCompanyAdmin().getUser().getId());
       BasicUser customer = basicUserRepository.findByUserId(purchaseOrderDTO.getCustomer().getUser().getId());
       Appointment appointment = appointmentRepository.getAppointmentById(purchaseOrderDTO.getAppointment().getId());
       appointment.setFree(false);
       appointmentRepository.save(appointment);

       PurchaseOrder   purchaseOrder=new PurchaseOrder();
       purchaseOrder.setAppointment(appointment);
       purchaseOrder.setCustomer(customer);
       purchaseOrder.setCompanyAdmin(companyAdmin);
       purchaseOrder.setStatus(PurchaseOrder.Status.ON_HOLD);

       PurchaseOrder savedPurchaseOrder=purchaseOrderRepository.save(purchaseOrder);


       List<OrderEquipment> orderEquipments=new ArrayList<>();
        for(OrderEquipmentDTO oe: purchaseOrderDTO.getOrderEquipments())
        {
            OrderEquipment orderEquipmentTEMP=new OrderEquipment();
            orderEquipmentTEMP.setOrder(savedPurchaseOrder);
            orderEquipmentTEMP.setQuantity(oe.getQuantity());

            Equipment equipment = equipmentRepository.findById(oe.getEquipment().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Equipment not found with ID: " + oe.getEquipment().getId()));

            orderEquipmentTEMP.setEquipment(equipment);
            orderEquipments.add(orderEquipmentTEMP);
        }

       List<OrderEquipment>  savedOrderEquipment=orderEquipmentRepository.saveAll(orderEquipments);




        savedPurchaseOrder.setOrderEquipments(savedOrderEquipment);

       try {
           emailService.sendReservationConfirmationEmail(savedPurchaseOrder);
       } catch (Exception e) {
           // Handle the exception as needed, e.g., log the error
           System.err.println("Failed to send confirmation email: " + e.getMessage());
       }
        // Save the purchase order to the database
        return purchaseOrderRepository.save(purchaseOrder);
    }
}
