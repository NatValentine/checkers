package com.natvalentine.config;

import com.natvalentine.IJSONMapper;
import com.natvalentine.JSONMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {
    @Bean
    public IJSONMapper jsonMapper(){
        return new JSONMap();
    }
}
