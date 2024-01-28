package com.medicines.distribution.controller;

import com.medicines.distribution.model.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
public class WebSocketController {

    @Autowired
    SimpMessagingTemplate messageTemplate;

    public void broadcastUpdatedOrders(Set<PurchaseOrder> updatedOrders) {
        messageTemplate.convertAndSend("/topic/orders", updatedOrders);
    }
}
