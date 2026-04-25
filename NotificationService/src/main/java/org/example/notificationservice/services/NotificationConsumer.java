package org.example.notificationservice.services;

import org.example.notificationservice.dtos.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumer {

    @KafkaListener(topics = "order-topic", groupId = "notification-group")
    public void consume(OrderEvent event) {

        System.out.println("Order ID: " + event.getOrderId());
        System.out.println("Email: " + event.getEmail());
        System.out.println("Message: " + event.getMessage());
    }
}
