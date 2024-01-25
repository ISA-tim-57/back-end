package com.medicines.distribution.service;

import com.medicines.distribution.repository.OrderEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderEquipmentService {

    @Autowired
    OrderEquipmentRepository orderEquipmentRepository;
}
