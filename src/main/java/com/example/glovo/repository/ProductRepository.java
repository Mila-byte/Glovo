package com.example.glovo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String addProduct = """
                insert into order_product(product_id, order_id) values (?, ?)
            """;

    private final String getTotalCost = """
                select sum(product.cost) as total_cost from order_product join product on order_product.product_id = product.id where order_product.order_id = ?
            """;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Float add(List<Integer> ids, Integer orderId) {
        for (Integer id : ids) {
            jdbcTemplate.update(addProduct, id, orderId);
        }

        return jdbcTemplate.queryForObject(getTotalCost, Float.class, orderId);
    }
}
