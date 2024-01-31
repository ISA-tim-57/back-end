package com.medicines.distribution.controller;

import com.medicines.distribution.model.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import java.util.Set;

@Controller
public class WebSocketController {

    @MessageMapping("/greet") // Define the message mapping URL
    @SendTo("/topic/greetings") // Define the topic where messages will be sent
    public String handleMessage(String message) {
        // Handle the received message
        return "Received message: " + message;
    }



}
