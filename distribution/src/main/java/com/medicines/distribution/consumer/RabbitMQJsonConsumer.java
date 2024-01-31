package com.medicines.distribution.consumer;

import com.medicines.distribution.dto.CoordinatesDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQJsonConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);

    private final SimpMessagingTemplate messagingTemplate;

    public RabbitMQJsonConsumer(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    public void consumeJsonMessage(CoordinatesDTO coordinates){
        LOGGER.info(String.format("Received JSON message -> %s", coordinates.toString()));
    }
}
