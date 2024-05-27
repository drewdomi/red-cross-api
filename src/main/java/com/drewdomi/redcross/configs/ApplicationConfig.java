package com.drewdomi.redcross.configs;

import com.drewdomi.redcross.repositories.RescuerRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfig {

    private final RescuerRespository rescuerRespository;

    @Autowired
    public ApplicationConfig(RescuerRespository rescuerRespository) {
        this.rescuerRespository = rescuerRespository;
    }

    private UserDetails loadRescuerByEmail(String username) {
        return rescuerRespository
            .findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("Rescuer not found"));
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return this::loadRescuerByEmail;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final var authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
