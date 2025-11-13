package com.dostawaex.deliverytracking;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DeliveryService {
    private final Map<String, Delivery> deliveries = new HashMap<>();
    private final SimpMessagingTemplate messagingTemplate;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public DeliveryService(SimpMessagingTemplate messagingTemplate, KafkaTemplate<String, String> kafkaTemplate) {
        this.messagingTemplate = messagingTemplate;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Delivery createDelivery(String orderId) {
        Delivery delivery = new Delivery();
        delivery.setOrderId(orderId);
        delivery.setStatus("Pending");
        delivery.setLocation("Warehouse");
        delivery.setTimestamp(System.currentTimeMillis());
        deliveries.put(orderId, delivery);
        return delivery;
    }

    public void updateStatus(String orderId, String status, String location) {
        Delivery delivery = deliveries.get(orderId);
        if (delivery != null) {
            delivery.setStatus(status);
            delivery.setLocation(location);
            delivery.setTimestamp(System.currentTimeMillis());
            // Publish to Kafka
            kafkaTemplate.send("delivery-events", orderId, status + " at " + location);
            // Send real-time update via WebSocket
            messagingTemplate.convertAndSend("/topic/delivery/" + orderId, delivery);
        }
    }

    public Delivery getDelivery(String orderId) {
        return deliveries.get(orderId);
    }
}