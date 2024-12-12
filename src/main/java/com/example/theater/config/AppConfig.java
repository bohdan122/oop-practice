package com.example.theater.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper; // Correct import for ModelMapper

@Configuration
public class AppConfig {
    @Bean
    public ModelMapper modelMapper() { // Correct method and class name
        return new ModelMapper();
    }
}
