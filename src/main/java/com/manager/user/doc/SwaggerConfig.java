package com.manager.user.doc;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .info(new Info()
                        .title("API RISING")
                        .description("API de gerenciamento")
                        .contact(new Contact()
                                .name("Matheus Nascimento")
                                .email("ma_eduard@hotmail.com"))
                );
    }

}
