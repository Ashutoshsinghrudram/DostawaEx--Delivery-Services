package com.dostawaex.deliverytracking;

import lombok.Data;

@Data
public class Delivery {
    private String orderId;
    private String status; // e.g., "Pending", "Picked Up", "In Transit", "Delivered"
    private String location; // e.g., "Lat: 40.7128, Lon: -74.0060"
    private long timestamp;
}