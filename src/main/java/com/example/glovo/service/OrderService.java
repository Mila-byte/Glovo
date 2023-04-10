package com.example.glovo.service;

import com.example.glovo.model.Order;
import com.example.glovo.model.OrderWithProducts;
import com.example.glovo.model.Product;
import com.example.glovo.repository.OrderRepository;
import com.example.glovo.repository.OrderWithProductsRepository;
import com.example.glovo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderWithProductsRepository orderWithProductsRepository;

    public Order get(int id) {
        return this.orderRepository.get(id);
    }

    public Map<Order, List<Product>> getWithProducts(int id) {
        return this.orderWithProductsRepository.getByOrderId(id).stream().collect(Collectors.toMap(OrderWithProducts::getOrder, s -> {
            ArrayList<Product> products = new ArrayList<>();
            products.add(s.getProduct());
            return products;
        }, (s, v) -> {
            s.addAll(v);
            return s;
        }));
    }

    public List<Order> getAll() {
        return this.orderRepository.getAll();
    }

    public Order save(List<Integer> ids) {
        Integer orderId = this.orderRepository.createOrder();
        Float totalCost = this.productRepository.add(ids, orderId);
        return this.orderRepository.add(totalCost, orderId);
    }
}
