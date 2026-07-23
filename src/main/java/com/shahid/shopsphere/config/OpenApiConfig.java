package com.shahid.shopsphere.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI shopSphereAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("ShopSphere API")
                        .description("E-Commerce Backend APIs")
                        .version("1.0"));
    }
}