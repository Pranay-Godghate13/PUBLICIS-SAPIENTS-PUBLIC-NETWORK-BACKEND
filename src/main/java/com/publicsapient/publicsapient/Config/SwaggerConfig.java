package com.publicsapient.publicsapient.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI()
    {
        return new OpenAPI()
                    .info(new Info()
                            .title("Spring Boot user API")
                            .version("1.0")
                            .description("This is a Spring Boot Project for fetching users")
                            .license(new License().name("Apache 2.0"))
                            .contact(new Contact()
                                        .name("Pranay Godghate")
                                        .email("pranaygodghate13@gmail.com")
                                        .url("https://www.linkedin.com/in/pranay-godghate-sde/")
                                        ));
    }
    
}
