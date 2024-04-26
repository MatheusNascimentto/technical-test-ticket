package com.manager.user.config;

import com.manager.user.service.DBservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {
    @Autowired
    private DBservice dbService;
    @Bean
    public void instanciaBaseDeDados() {
        this.dbService.instanciaBaseDeDados();
    }
}
