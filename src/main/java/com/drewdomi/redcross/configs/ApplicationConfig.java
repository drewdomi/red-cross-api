package com.drewdomi.redcross.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.drewdomi.redcross.repositories.RescuerRespository;

@Configuration
public class ApplicationConfig {

    private RescuerRespository rescuerRespository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            return rescuerRespository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Rescuer not found"));
        };
    }

}
