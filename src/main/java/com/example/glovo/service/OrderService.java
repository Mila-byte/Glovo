package com.example.glovo.service;

import com.example.glovo.model.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class OrderService {
    private final List<Order> orders = new ArrayList<>();
    private final Random random = new Random();

    public Order get(int id) {
        return orders.stream().filter(order -> order.getId() == id).findFirst().orElseThrow();
    }

    public List<Order> getAll() {
        return this.orders;
    }

    public Order save(Order order) {
        order.setId(this.random.nextInt());
        orders.add(order);
        return order;
    }
}
