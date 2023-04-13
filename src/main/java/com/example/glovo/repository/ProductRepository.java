package com.example.glovo.repository;

import com.example.glovo.model.Product;
import com.example.glovo.repository.mapper.ProductRowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

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

    private final String getById = """
                select * from product where id =
            """;

    private final String getAll = """
                select * from product
            """;

    private final String create = """
                insert into product (name, cost) values (?, ?)
            """;

    private final String update = """
                update product set name = COALESCE(?, name), cost = CASE WHEN ? = 0.0 THEN cost ELSE ? END WHERE id = ?
            """;

    private final String delete = """
                delete from product where id = ?
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

    public Product get(int id) {
        try {
            return jdbcTemplate.queryForObject(getById + id, new ProductRowMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Not found", e
            );
        }
    }

    public List<Product> getAll() {
        return jdbcTemplate.query(getAll, new ProductRowMapper());
    }

    public int save(Product product) {
        return jdbcTemplate.update(create, product.getName(), product.getCost());
    }

    public int update(int id, Product product) {
        return jdbcTemplate.update(update, product.getName(), product.getCost(), product.getCost(), id);
    }

    public int delete(int id) {
        return jdbcTemplate.update(delete, id);
    }
}
