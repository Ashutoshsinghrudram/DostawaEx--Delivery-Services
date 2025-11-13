package com.dostawaex.deliverytracking;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DeliveryEventListener {
    @KafkaListener(topics = "delivery-events", groupId = "delivery-group")
    public void listen(String message) {
        System.out.println("Received event: " + message);
        // In a real scenario, process the event (e.g., update database)
    }
}