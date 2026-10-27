package com.example.mall.repository;

import com.example.mall.model.Models.Page;
import com.example.mall.model.Models.Product;
import com.example.mall.model.Models.StockMovement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class StockRepository {
    private final JdbcTemplate jdbc;
    private static final RowMapper<StockMovement> MAPPER = (rs, row) -> new StockMovement(rs.getLong("id"),
            rs.getLong("product_id"), rs.getString("product_name"), rs.getString("sku"), rs.getString("type"),
            rs.getInt("delta"), rs.getInt("before_stock"), rs.getInt("after_stock"), rs.getString("reference"),
            rs.getString("reason"), rs.getString("remark"), rs.getString("operator"),
            rs.getTimestamp("created_at").toLocalDateTime());

    public StockRepository(JdbcTemplate jdbc) { this.jdbc = jdbc; }

    public Optional<StockMovement> find(long productId, String type, String reference) {
        return jdbc.query("SELECT * FROM stock_movement WHERE product_id = ? AND type = ? AND reference = ?",
                MAPPER, productId, type, reference).stream().findFirst();
    }

    public StockMovement insert(Product product, int delta, String type, String reference, String reason,
            String remark, String operator) {
        jdbc.update("INSERT INTO stock_movement(product_id, product_name, sku, type, delta, before_stock, after_stock, reference, reason, remark, operator) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                product.id(), product.name(), product.sku(), type, delta, product.stock(), product.stock() + delta,
                reference, reason, remark, operator);
        return find(product.id(), type, reference).orElseThrow();
    }

    public long countByProduct(long id) {
        return jdbc.queryForObject("SELECT COUNT(*) FROM stock_movement WHERE product_id = ?", Long.class, id);
    }

    public Page<StockMovement> findPage(String keyword, String type, LocalDate start, LocalDate end, int page, int size) {
        var where = new StringBuilder(" WHERE 1 = 1");
        var args = new ArrayList<Object>();
        if (keyword != null && !keyword.isBlank()) {
            where.append(" AND (LOWER(product_name) LIKE ? ESCAPE '!' OR LOWER(sku) LIKE ? ESCAPE '!' OR LOWER(reference) LIKE ? ESCAPE '!')");
            String pattern = "%" + ProductRepository.escapeLike(keyword.trim().toLowerCase(java.util.Locale.ROOT)) + "%";
            args.add(pattern); args.add(pattern); args.add(pattern);
        }
        if (type != null && !type.isBlank()) { where.append(" AND type = ?"); args.add(type); }
        if (start != null) { where.append(" AND created_at >= ?"); args.add(start.atStartOfDay()); }
        if (end != null) { where.append(" AND created_at < ?"); args.add(end.plusDays(1).atStartOfDay()); }
        long total = jdbc.queryForObject("SELECT COUNT(*) FROM stock_movement" + where, Long.class, args.toArray());
        args.add(size); args.add((long) (page - 1) * size);
        return new Page<>(jdbc.query("SELECT * FROM stock_movement" + where + " ORDER BY id DESC LIMIT ? OFFSET ?",
                MAPPER, args.toArray()), total, page, size);
    }
}
