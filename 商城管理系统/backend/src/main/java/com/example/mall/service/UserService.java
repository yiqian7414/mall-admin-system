package com.example.mall.service;

import com.example.mall.common.BusinessException;
import com.example.mall.model.Models.User;
import com.example.mall.model.Requests.CreateUserInput;
import com.example.mall.model.Requests.UpdateUserInput;
import com.example.mall.repository.UserRepository;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository users;
    private final PasswordEncoder encoder;

    public UserService(UserRepository users, PasswordEncoder encoder) {
        this.users = users;
        this.encoder = encoder;
    }

    public List<User> findAll() {
        return users.findAll();
    }

    @Transactional
    public User create(CreateUserInput input) {
        checkPassword(input.password());
        long id = users.insert(input.username(), input.displayName().trim(), encoder.encode(input.password()),
                input.role(), input.enabled());
        return users.findById(id).orElseThrow().user();
    }

    @Transactional
    public User update(long id, UpdateUserInput input, long currentUserId) {
        var administrators = users.lockEnabledAdministrators();
        var account = users.findById(id).orElseThrow(() -> BusinessException.notFound("用户不存在"));
        boolean removesAdmin = account.user().role().equals("ADMIN") && account.user().enabled()
                && (!input.role().equals("ADMIN") || !input.enabled());
        if (removesAdmin && administrators.size() <= 1)
            throw BusinessException.conflict("不能停用或降级最后一个启用的管理员");
        if (id == currentUserId && (!input.enabled() || !input.role().equals("ADMIN"))) {
            throw BusinessException.conflict("不能停用或降级当前登录账号");
        }
        String hash = account.passwordHash();
        if (input.password() != null && !input.password().isEmpty()) {
            checkPassword(input.password());
            hash = encoder.encode(input.password());
        }
        users.update(id, input.displayName().trim(), input.role(), input.enabled(), hash);
        return users.findById(id).orElseThrow().user();
    }

    @Transactional
    public void disable(long id, long currentUserId) {
        var administrators = users.lockEnabledAdministrators();
        var account = users.findById(id).orElseThrow(() -> BusinessException.notFound("用户不存在"));
        if (id == currentUserId)
            throw BusinessException.conflict("不能停用当前登录账号");
        if (account.user().role().equals("ADMIN") && account.user().enabled() && administrators.size() <= 1) {
            throw BusinessException.conflict("不能停用最后一个启用的管理员");
        }
        users.disable(id);
    }

    private void checkPassword(String password) {
        if (password.length() < 8 || password.length() > 64 || password.getBytes(StandardCharsets.UTF_8).length > 72) {
            throw BusinessException.badRequest("密码须为 8 至 64 个字符，UTF-8 编码不能超过 72 字节");
        }
    }
}
