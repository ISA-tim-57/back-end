package com.medicines.distribution.controller;

import com.medicines.distribution.service.OrderEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/orderequipment")
public class OrderEqupmentController {

    @Autowired
    OrderEquipmentService orderEquipmentService;
}
