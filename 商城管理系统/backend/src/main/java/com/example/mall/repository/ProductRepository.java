package com.example.mall.repository;

import com.example.mall.model.Models.Page;
import com.example.mall.model.Models.Product;
import com.example.mall.model.Requests.ProductInput;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {
    private final JdbcTemplate jdbc;
    private static final String SELECT = "SELECT p.*, c.name AS category_name FROM product p JOIN category c ON c.id = p.category_id";
    private static final RowMapper<Product> MAPPER = (rs, row) -> new Product(rs.getLong("id"), rs.getString("name"),
            rs.getString("sku"), rs.getLong("category_id"), rs.getString("category_name"), rs.getBigDecimal("price"),
            rs.getInt("stock"), rs.getString("status"), rs.getString("description"),
            rs.getTimestamp("created_at").toLocalDateTime(), rs.getString("image_url"));

    public ProductRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Optional<Product> findById(long id) {
        return jdbc.query(SELECT + " WHERE p.id = ?", MAPPER, id).stream().findFirst();
    }

    /** 事务里锁定商品行。按 id 顺序调用，避免多商品订单相互逆序等待。 */
    public Optional<Product> lockById(long id) {
        return jdbc.query(SELECT + " WHERE p.id = ? FOR UPDATE", MAPPER, id).stream().findFirst();
    }

    public Page<Product> findPage(String keyword, Long categoryId, String status, int page, int size) {
        var where = new StringBuilder(" WHERE 1 = 1");
        var parameters = new ArrayList<Object>();
        if (keyword != null && !keyword.isBlank()) {
            where.append(" AND (LOWER(p.name) LIKE ? ESCAPE '!' OR LOWER(p.sku) LIKE ? ESCAPE '!')");
            String pattern = "%" + escapeLike(keyword.trim().toLowerCase(java.util.Locale.ROOT)) + "%";
            parameters.add(pattern);
            parameters.add(pattern);
        }
        if (categoryId != null) {
            where.append(" AND p.category_id = ?");
            parameters.add(categoryId);
        }
        if (status != null && !status.isBlank()) {
            where.append(" AND p.status = ?");
            parameters.add(status);
        }
        long total = jdbc.queryForObject("SELECT COUNT(*) FROM product p" + where, Long.class, parameters.toArray());
        parameters.add(size);
        parameters.add((long) (page - 1) * size);
        var items = jdbc.query(SELECT + where + " ORDER BY p.id DESC LIMIT ? OFFSET ?", MAPPER, parameters.toArray());
        return new Page<>(items, total, page, size);
    }

    public long insert(ProductInput input) {
        var keys = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO product(name, sku, category_id, price, stock, status, description, image_url) VALUES (?, ?, ?, ?, 0, ?, ?, ?)",
                    new String[] { "id" });
            statement.setString(1, input.name().trim());
            statement.setString(2, input.sku());
            statement.setLong(3, input.categoryId());
            statement.setBigDecimal(4, input.price());
            statement.setString(5, input.status());
            statement.setString(6, input.description() == null ? "" : input.description().trim());
            statement.setString(7, input.imageUrl() == null ? "" : input.imageUrl());
            return statement;
        }, keys);
        return keys.getKey().longValue();
    }

    public void update(long id, ProductInput input) {
        jdbc.update(
                "UPDATE product SET name = ?, sku = ?, category_id = ?, price = ?, status = ?, description = ?, image_url = ? WHERE id = ?",
                input.name().trim(), input.sku(), input.categoryId(), input.price(), input.status(),
                input.description() == null ? "" : input.description().trim(), input.imageUrl() == null ? "" : input.imageUrl(), id);
    }

    public int adjustStock(long id, int delta) {
        return jdbc.update("UPDATE product SET stock = stock + ? WHERE id = ? AND stock + ? BETWEEN 0 AND 1000000000",
                delta, id, delta);
    }

    public void updateStatus(long id, String status) {
        jdbc.update("UPDATE product SET status = ? WHERE id = ?", status, id);
    }

    public long countOrderReferences(long id) {
        return jdbc.queryForObject("SELECT COUNT(*) FROM order_item WHERE product_id = ?", Long.class, id);
    }

    public void delete(long id) {
        jdbc.update("DELETE FROM product WHERE id = ?", id);
    }

    public List<Product> lowStockProducts() {
        return jdbc.query(SELECT + " WHERE p.stock <= 5 ORDER BY p.stock, p.id LIMIT 8", MAPPER);
    }

    static String escapeLike(String value) {
        return value.replace("!", "!!").replace("%", "!%").replace("_", "!_");
    }
}
