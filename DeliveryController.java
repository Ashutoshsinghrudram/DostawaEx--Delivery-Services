package com.dostawaex.deliverytracking;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {
    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @PostMapping("/{orderId}")
    public Delivery createDelivery(@PathVariable String orderId) {
        return deliveryService.createDelivery(orderId);
    }

    @PutMapping("/{orderId}/status")
    public void updateStatus(@PathVariable String orderId, @RequestParam String status, @RequestParam String location) {
        deliveryService.updateStatus(orderId, status, location);
    }

    @GetMapping("/{orderId}")
    public Delivery getDelivery(@PathVariable String orderId) {
        return deliveryService.getDelivery(orderId);
    }
}