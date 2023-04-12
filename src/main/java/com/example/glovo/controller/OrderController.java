package com.example.glovo.controller;

import com.example.glovo.model.Order;
import com.example.glovo.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Integer id) {
        return this.orderService.get(id);
    }

    @GetMapping
    public List<Order> getOrders() {
        return this.orderService.getAll();
    }

    @PostMapping
    public Order save(@RequestBody Order order) {
        return this.orderService.save(order);
    }
}
