package com.practice.social_network.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class SecurityConfig {
    private static final String[] AUTH_WHITE_LIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/v2/api-docs/**",
            "/swagger-resources/**",
            "/user"
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
                        .requestMatchers(AUTH_WHITE_LIST).permitAll()
                        .requestMatchers(HttpMethod.GET, "/posts/**").permitAll()
                        .requestMatchers("/posts/**").hasAnyRole("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/post-comment/**").permitAll()
                        .requestMatchers("/post-comment/**").hasAnyRole("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers("/post-like/**").hasAnyRole("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers(HttpMethod.GET, "/user/**").permitAll()
                        .requestMatchers("/user/**").hasAnyRole("ROLE_USER", "ROLE_ADMIN")
                        .requestMatchers("/user-follow/**").hasAnyRole("ROLE_USER", "ROLE_ADMIN")
                );
        return http.build();
    }

}
