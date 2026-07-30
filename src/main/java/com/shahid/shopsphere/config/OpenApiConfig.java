package com.shahid.shopsphere.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
    name = "Bearer Authentication",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT"
)
public class OpenApiConfig {

    @Bean
    public OpenAPI shopSphereAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ShopSphere REST API")
                        .description("Production-style E-Commerce REST API built using Spring Boot.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Syed Shahid Ahamed")
                                .email("shahidahamedsyed@gmail.com")
                                .url("https://github.com/SyedShahidAhamed"))
                        .license(new License()
                                .name("MIT License")));
    }
}