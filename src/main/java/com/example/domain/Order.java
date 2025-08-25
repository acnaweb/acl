package com.example.domain;
import java.util.List;
public record Order(String id, OrderStatus status, List<OrderItem> items) {}