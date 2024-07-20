package com.practice.social_network.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    private static final String[] AUTH_WHITE_LIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/v2/api-docs/**",
            "/swagger-resources/**",
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/posts/**").permitAll()
                        .requestMatchers("/posts/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/post-comment/**").permitAll()
                        .requestMatchers("/post-comment/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/post-like/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/user/**").permitAll()
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/user-follow/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers(AUTH_WHITE_LIST).permitAll()
                );
        return http.build();
    }

}
