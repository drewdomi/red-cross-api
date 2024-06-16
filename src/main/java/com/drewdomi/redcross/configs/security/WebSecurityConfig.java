package com.drewdomi.redcross.configs.security;

import com.drewdomi.redcross.configs.jwt.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final JwtAuthFilter jwtAuthenticationFilter;

    private final AuthenticationProvider authenticationProvider;

    private static final String[] WHITE_LIST_ENDPOINTS = {
        "/auth",
        "/rescuer"
    };
    private static final String[] ADMIN_ROLES_LIST_ENDPOINTS = {
        "/escuer/**",
    };

    public WebSecurityConfig(
        JwtAuthFilter jwtAuthenticationFilter,
        AuthenticationProvider authenticationProvider
    ) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth ->
            auth
                .requestMatchers(WHITE_LIST_ENDPOINTS)
                .permitAll()
                .requestMatchers(ADMIN_ROLES_LIST_ENDPOINTS)
                .hasAuthority("ADMIN")
                .anyRequest()
                .authenticated()
        );
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.authenticationProvider(authenticationProvider);
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
