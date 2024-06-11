package com.medicines.distribution.service;

import com.medicines.distribution.dto.OrderEquipmentDTO;
import com.medicines.distribution.model.Appointment;
import com.medicines.distribution.model.Equipment;
import com.medicines.distribution.model.OrderEquipment;
import com.medicines.distribution.model.PurchaseOrder;
import com.medicines.distribution.repository.Appointmentrepository;
import com.medicines.distribution.repository.EquipmentRepository;
import com.medicines.distribution.repository.OrderEquipmentRepository;
import com.medicines.distribution.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderEquipmentService {

    @Autowired
    OrderEquipmentRepository orderEquipmentRepository;

    @Autowired
    PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    EquipmentRepository equipmentRepository;



    @Autowired
    Appointmentrepository appointmentRepository;


    public List<OrderEquipment>  saveOrderEquipments(List<OrderEquipmentDTO> orderEquipmentDTOList) {
        List<OrderEquipment> orderEquipments = new ArrayList<>();
        for (OrderEquipmentDTO dto : orderEquipmentDTOList) {
            Equipment equipment = equipmentRepository.findById(dto.getEquipment().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Equipment ID: " + dto.getEquipment().getId()));
            OrderEquipment orderEquipment = new OrderEquipment(dto.getId(), null, equipment, dto.getQuantity());
            orderEquipments.add(orderEquipment);
        }
        return orderEquipmentRepository.saveAll(orderEquipments);
    }



}
