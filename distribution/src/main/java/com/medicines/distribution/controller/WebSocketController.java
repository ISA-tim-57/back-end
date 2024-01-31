package com.medicines.distribution.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medicines.distribution.dto.CoordinatesDTO;
import com.medicines.distribution.model.PurchaseOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Controller
public class WebSocketController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/sendMessageRest", method = RequestMethod.POST)
    public ResponseEntity<?> sendMessage(@RequestBody CoordinatesDTO message) {

        this.simpMessagingTemplate.convertAndSend("/socket-publisher", message);
        return new ResponseEntity<>(message, new HttpHeaders(), HttpStatus.OK);
    }

    @MessageMapping("/send/message")
    public CoordinatesDTO broadcastNotification(CoordinatesDTO coordinates) {
        this.simpMessagingTemplate.convertAndSend("/socket-publisher", coordinates);
        return coordinates;
    }




}
