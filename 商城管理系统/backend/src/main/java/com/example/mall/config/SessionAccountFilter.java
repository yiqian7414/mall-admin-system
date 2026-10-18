package com.example.mall.config;

import com.example.mall.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/** 每次请求重新确认账号状态，使停用、角色变更和改密码对已有会话同样生效。 */
public class SessionAccountFilter extends OncePerRequestFilter {
    private final UserRepository users;
    private final ObjectMapper mapper;

    public SessionAccountFilter(UserRepository users, ObjectMapper mapper) {
        this.users = users;
        this.mapper = mapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof MallPrincipal principal) {
            var latest = users.findById(principal.user().id());
            if (latest.isEmpty() || !latest.get().user().enabled()
                    || !latest.get().passwordHash().equals(principal.getPassword())) {
                SecurityContextHolder.clearContext();
                var session = request.getSession(false);
                if (session != null)
                    session.invalidate();
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json;charset=UTF-8");
                mapper.writeValue(response.getOutputStream(), Map.of("message", "账号状态或密码已变化，请重新登录"));
                return;
            }
            var refreshed = new MallPrincipal(latest.get());
            var token = UsernamePasswordAuthenticationToken.authenticated(refreshed, null, refreshed.getAuthorities());
            token.setDetails(authentication.getDetails());
            SecurityContextHolder.getContext().setAuthentication(token);
        }
        chain.doFilter(request, response);
    }
}
