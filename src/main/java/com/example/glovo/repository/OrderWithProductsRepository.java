package com.example.glovo.repository;

import com.example.glovo.model.OrderWithProducts;
import com.example.glovo.repository.mapper.OrderWithProductsMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderWithProductsRepository {
    private final JdbcTemplate jdbcTemplate;
    private final String getByOrderId = """
                select order.*, product.* from order
                inner join order_product on order.order_id = order_product.order_id
                inner join product on order_product.product_id = product.id where order.order_id = 
            """;

    public OrderWithProductsRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<OrderWithProducts> getByOrderId(int id) {
        return this.jdbcTemplate.query(getByOrderId + id, new OrderWithProductsMapper());
    }
}
