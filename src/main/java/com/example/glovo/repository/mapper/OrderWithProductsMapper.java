package com.example.glovo.repository.mapper;

import com.example.glovo.model.Order;
import com.example.glovo.model.OrderWithProducts;
import com.example.glovo.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderWithProductsMapper implements RowMapper<OrderWithProducts> {
    @Override
    public OrderWithProducts mapRow(ResultSet rs, int rowNum) throws SQLException {
        Order order = Order.builder()
                .id(rs.getInt(1))
                .date(rs.getDate(2))
                .cost(rs.getFloat(3))
                .build();

        Product product = Product.builder()
                .id(rs.getInt(4))
                .name(rs.getString(5))
                .cost(rs.getFloat(6))
                .build();

        return OrderWithProducts.builder()
                .order(order)
                .product(product)
                .build();
    }
}
