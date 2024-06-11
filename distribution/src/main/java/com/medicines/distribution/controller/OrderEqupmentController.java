package com.medicines.distribution.controller;

import com.medicines.distribution.dto.OrderEquipmentDTO;
import com.medicines.distribution.service.OrderEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/orderequipment")
public class OrderEqupmentController {

    @Autowired
    OrderEquipmentService orderEquipmentService;





}
