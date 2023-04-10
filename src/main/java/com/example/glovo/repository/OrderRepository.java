package com.example.glovo.repository;

import com.example.glovo.model.Order;
import com.example.glovo.repository.mapper.OrderRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Repository
public class OrderRepository {
    private final JdbcTemplate jdbcTemplate;

    private final String getById = """
                select * from order where order_id = 
            """;
    private final String getAll = """
                select * from order
            """;

    private final String createOrder = """
            insert into order (date, cost) values (current_date, 0) returning order_id
            """;

    private final String updateOrder = """
                update order set cost = ? where order_id = ?
            """;


    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Order get(int id) {
        try {
            return jdbcTemplate.queryForObject(getById + id, new OrderRowMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not found", e
            );
        }
    }

    public List<Order> getAll() {
        return jdbcTemplate.query(getAll, new OrderRowMapper());
    }

    public Integer createOrder() {
        return jdbcTemplate.queryForObject(createOrder, Integer.class);
    }

    public Order add(Float totalCost, Integer orderId) {
        jdbcTemplate.update(updateOrder, totalCost, orderId);
        return jdbcTemplate.queryForObject(getById + orderId, new OrderRowMapper());
    }
}
