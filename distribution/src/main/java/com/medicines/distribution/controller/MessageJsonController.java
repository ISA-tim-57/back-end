package com.medicines.distribution.controller;

import com.medicines.distribution.dto.CoordinatesDTO;
import com.medicines.distribution.publisher.RabbitMQJsonProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageJsonController {

    private RabbitMQJsonProducer jsonProducer;

    public MessageJsonController(RabbitMQJsonProducer rabbitMQJsonProducer) {
        this.jsonProducer = rabbitMQJsonProducer;
    }

    @PostMapping("/publish")
    public ResponseEntity<CoordinatesDTO> sendJsonMessage(@RequestBody CoordinatesDTO cooridnates){
        jsonProducer.sendJsonMessage(cooridnates);
        return new ResponseEntity<>(cooridnates, HttpStatus.OK);
    }
}
