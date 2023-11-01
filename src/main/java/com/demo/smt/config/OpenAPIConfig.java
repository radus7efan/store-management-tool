package com.demo.smt.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class OpenAPIConfig {

    private static final String DESCRIPTION = """
            Rest API definition of the store management tool application.
                                               
            Useful links:
               - [Github repository](https://github.com/radus7efan/store-management-tool)
               - [Readme](https://github.com/radus7efan/store-management-tool/blob/main/README.md)
               """;

    @Bean
    public OpenAPI serviceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Store Management Tool")
                        .description(DESCRIPTION)
                        .contact(
                                new Contact()
                                        .name("Radu Stefan")
                                        .email("radustef.an@yahoo.ro")
                                        .url("https://github.com/radus7efan")
                        )
                        .version("1.0.0")
                )
                .schemaRequirement(
                        "bearerAuth",
                        new SecurityScheme()
                                .name("bearerAuth")
                                .type(SecurityScheme.Type.HTTP)
                                .in(SecurityScheme.In.HEADER)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                );
    }
}
