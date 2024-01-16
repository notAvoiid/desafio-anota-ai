package com.imrzoom.desafioanotaai.config.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot with Java 17!")
                        .version("v1")
                        .description("Todo List!")
                        .termsOfService("https://github.com/notAvoiid")
                        .license(
                                new License()
                                        .name("Apache 2.0")
                                        .url("https://github.com/notAvoiid")));
    }

}
