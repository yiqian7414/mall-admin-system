package com.example.mall.repository;

import com.example.mall.model.Models.User;
import com.example.mall.model.Models.UserAccount;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbc;
    private static final RowMapper<UserAccount> MAPPER = (rs, row) -> new UserAccount(new User(rs.getLong("id"),
            rs.getString("username"), rs.getString("display_name"), rs.getString("role"), rs.getBoolean("enabled")),
            rs.getString("password_hash"));

    public UserRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public Optional<UserAccount> findByUsername(String username) {
        return jdbc.query("SELECT * FROM mall_user WHERE username = ?", MAPPER, username).stream().findFirst();
    }

    public Optional<UserAccount> findById(long id) {
        return jdbc.query("SELECT * FROM mall_user WHERE id = ?", MAPPER, id).stream().findFirst();
    }

    public List<User> findAll() {
        return jdbc.query("SELECT * FROM mall_user ORDER BY id", MAPPER).stream().map(UserAccount::user).toList();
    }

    public long insert(String username, String displayName, String hash, String role, boolean enabled) {
        var keys = new GeneratedKeyHolder();
        jdbc.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO mall_user(username, display_name, password_hash, role, enabled) VALUES (?, ?, ?, ?, ?)",
                    new String[] { "id" });
            statement.setString(1, username);
            statement.setString(2, displayName);
            statement.setString(3, hash);
            statement.setString(4, role);
            statement.setBoolean(5, enabled);
            return statement;
        }, keys);
        return keys.getKey().longValue();
    }

    public void update(long id, String displayName, String role, boolean enabled, String hash) {
        jdbc.update("UPDATE mall_user SET display_name = ?, role = ?, enabled = ?, password_hash = ? WHERE id = ?",
                displayName, role, enabled, hash, id);
    }

    public void disable(long id) {
        jdbc.update("UPDATE mall_user SET enabled = FALSE WHERE id = ?", id);
    }

    public List<Long> lockEnabledAdministrators() {
        return jdbc.query("SELECT id FROM mall_user WHERE role = 'ADMIN' AND enabled = TRUE ORDER BY id FOR UPDATE",
                (rs, row) -> rs.getLong(1));
    }
}
