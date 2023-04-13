package com.example.glovo.service;

import com.example.glovo.model.Product;
import com.example.glovo.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product get(int id) {
        return this.productRepository.get(id);
    }

    public List<Product> getAll() {
        return this.productRepository.getAll();
    }

    public int save(Product product) {
        return this.productRepository.save(product);
    }

    public int update(int id, Product product) {
        return this.productRepository.update(id, product);
    }

    public int delete(int id) {
        return this.productRepository.delete(id);
    }
}
