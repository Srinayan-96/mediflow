package com.example.medicore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // This stops the "403 Forbidden" on POST
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // Allows all requests
                .httpBasic(withDefaults());
        return http.build();
    }
}
