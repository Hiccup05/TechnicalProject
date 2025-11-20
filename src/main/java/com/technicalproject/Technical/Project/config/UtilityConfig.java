package com.technicalproject.Technical.Project.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class UtilityConfig {

    @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }

    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
