package com.example.webflux.config;

import com.example.webflux.logging.ReactiveFilter;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingConfig {

    private String[] ignorePaths() {
        return new String[]{};
    }

    @Bean
    public ReactiveFilter reactiveSpringLoggingFilter() {
        return ReactiveFilter.builder().ignorePaths(ignorePaths()).build();
    }

}
