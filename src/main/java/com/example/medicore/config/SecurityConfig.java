    package com.example.medicore.config;

    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.security.authentication.AuthenticationManager;
    import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
    import org.springframework.security.config.annotation.web.builders.HttpSecurity;
    import org.springframework.security.web.SecurityFilterChain;

    import static org.springframework.security.config.Customizer.withDefaults;

    @Configuration
    public class SecurityConfig {
        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable()) // This stops the "403 Forbidden" on POST
                    .authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll().anyRequest().authenticated()); // Allows all requests
            return http.build();
        }
        @Bean
        public AuthenticationManager authenticationManager(
                AuthenticationConfiguration config) throws Exception {

            return config.getAuthenticationManager();
        }

        @Bean
        public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
            return org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance();
        }
    }
