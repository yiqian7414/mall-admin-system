package com.example.mall.controller;

import com.example.mall.config.MallPrincipal;
import com.example.mall.model.Models.User;
import com.example.mall.model.Requests.CreateUserInput;
import com.example.mall.model.Requests.UpdateUserInput;
import com.example.mall.service.UserService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService users;

    public UserController(UserService users) {
        this.users = users;
    }

    @GetMapping
    public List<User> list() {
        return users.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@Valid @RequestBody CreateUserInput input) {
        return users.create(input);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable long id, @Valid @RequestBody UpdateUserInput input,
            @AuthenticationPrincipal MallPrincipal principal) {
        return users.update(id, input, principal.user().id());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void disable(@PathVariable long id, @AuthenticationPrincipal MallPrincipal principal) {
        users.disable(id, principal.user().id());
    }
}
