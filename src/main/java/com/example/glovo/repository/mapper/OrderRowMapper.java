package com.example.glovo.repository.mapper;

import com.example.glovo.model.Order;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {

    @Override
    public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Order.builder()
                .id(rs.getInt(1))
                .date(rs.getDate(2))
                .cost(rs.getFloat(3))
                .build();
    }
}
