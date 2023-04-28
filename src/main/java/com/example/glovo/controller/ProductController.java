package com.example.glovo.controller;

import com.example.glovo.model.Product;
import com.example.glovo.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Integer id) {
        return this.productService.get(id);
    }

    @GetMapping
    public List<Product> getProducts() {
        return this.productService.getAll();
    }

    @PostMapping
    public int save(@RequestBody Product product) {
        return this.productService.save(product);
    }

    @PutMapping("/{id}")
    public int update(@PathVariable Integer id, @RequestBody Product product) {
        return this.productService.update(id, product);
    }

    @DeleteMapping("/{id}")
    public int delete(@PathVariable Integer id) {
        return this.productService.delete(id);
    }
}
