package com.example.mall.config;

import com.example.mall.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;

@Configuration
public class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(UserRepository users) {
        return username -> users.findByUsername(username).map(MallPrincipal::new)
                .orElseThrow(() -> new UsernameNotFoundException("用户名或密码错误"));
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http, UserRepository users, ObjectMapper mapper)
            throws Exception {
        // API 直接返回原始会话 CSRF token；前端通过 X-CSRF-TOKEN 请求头回传。
        var csrfHandler = new CsrfTokenRequestAttributeHandler();
        http.csrf(csrf -> csrf.csrfTokenRequestHandler(csrfHandler))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/health", "/api/auth/csrf", "/api/auth/login", "/error").permitAll()
                        .requestMatchers("/api/users", "/api/users/**").hasRole("ADMIN").anyRequest().authenticated())
                .formLogin(login -> login.loginProcessingUrl("/api/auth/login")
                        .successHandler((request, response, authentication) -> write(mapper, response, 200,
                                ((MallPrincipal) authentication.getPrincipal()).user()))
                        .failureHandler((request, response, exception) -> write(mapper, response, 401,
                                Map.of("message", "用户名或密码错误，或账号已停用"))))
                .logout(logout -> logout.logoutUrl("/api/auth/logout").invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .logoutSuccessHandler((request, response, authentication) -> write(mapper, response, 200,
                                Map.of("message", "已退出登录"))))
                .exceptionHandling(errors -> errors.authenticationEntryPoint(
                        (request, response, exception) -> write(mapper, response, 401, Map.of("message", "请先登录")))
                        .accessDeniedHandler((request, response, exception) -> write(mapper, response, 403,
                                Map.of("message", "权限不足或安全令牌已失效，请刷新后重试"))))
                .requestCache(cache -> cache.disable())
                .addFilterBefore(new SessionAccountFilter(users, mapper), AuthorizationFilter.class);
        return http.build();
    }

    private static void write(ObjectMapper mapper, HttpServletResponse response, int status, Object body)
            throws IOException {
        response.setStatus(status);
        response.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(response.getOutputStream(), body);
    }
}
