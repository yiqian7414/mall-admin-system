package com.example.mall.repository;

import com.example.mall.model.Models.Category;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepository {
    private final JdbcTemplate jdbc;
    private static final RowMapper<Category> MAPPER = (rs, row) -> new Category(rs.getLong("id"), rs.getString("name"),
            rs.getString("description"));

    public CategoryRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Category> findAll() {
        return jdbc.query("SELECT * FROM category ORDER BY id", MAPPER);
    }

    public Optional<Category> findById(long id) {
        return jdbc.query("SELECT * FROM category WHERE id = ?", MAPPER, id).stream().findFirst();
    }

    public long insert(String name, String description) {
        var keys = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement("INSERT INTO category(name, description) VALUES (?, ?)", new String[] { "id" });
            statement.setString(1, name);
            statement.setString(2, description);
            return statement;
        }, keys);
        return keys.getKey().longValue();
    }

    public void update(long id, String name, String description) {
        jdbc.update("UPDATE category SET name = ?, description = ? WHERE id = ?", name, description, id);
    }

    public long countProducts(long id) {
        return jdbc.queryForObject("SELECT COUNT(*) FROM product WHERE category_id = ?", Long.class, id);
    }

    public void delete(long id) {
        jdbc.update("DELETE FROM category WHERE id = ?", id);
    }
}
