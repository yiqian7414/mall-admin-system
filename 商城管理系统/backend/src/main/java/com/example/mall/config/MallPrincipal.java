package com.example.mall.config;

import com.example.mall.model.Models.User;
import com.example.mall.model.Models.UserAccount;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MallPrincipal implements UserDetails {
    private final UserAccount account;

    public MallPrincipal(UserAccount account) {
        this.account = account;
    }

    public User user() {
        return account.user();
    }

    @Override
    public String getUsername() {
        return account.user().username();
    }

    @Override
    public String getPassword() {
        return account.passwordHash();
    }

    @Override
    public boolean isEnabled() {
        return account.user().enabled();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + account.user().role()));
    }
}
