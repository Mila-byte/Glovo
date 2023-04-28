package com.example.glovo.controller;

import com.example.glovo.model.Order;
import com.example.glovo.model.Product;
import com.example.glovo.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Integer id) {
        return this.orderService.get(id);
    }

    @GetMapping("/{id}/products")
    public Map<Order, List<Product>> getWithProduct(@PathVariable int id) {
        return orderService.getWithProducts(id);
    }

    @GetMapping
    public List<Order> getOrders() {
        return this.orderService.getAll();
    }

    @PostMapping
    public Order save(@RequestBody List<Integer> productsId) {
        return this.orderService.save(productsId);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable Integer id) {
        return this.orderService.delete(id);
    }

    @DeleteMapping("/{id}/products/{productId}")
    public int deleteProduct(@PathVariable Integer id, @PathVariable Integer productId) {
        return this.orderService.deleteProduct(id, productId);
    }
}
