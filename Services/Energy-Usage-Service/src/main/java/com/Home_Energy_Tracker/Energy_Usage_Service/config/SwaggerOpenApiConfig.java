package com.Home_Energy_Tracker.Energy_Usage_Service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class SwaggerOpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Home Energy Tracking System API - Energy Usage Service")
                                .version("1.0")
                                .contact(new Contact().email("mohammed.khadeeruddin@ctlup.com"))
                                .description("API for querying aggregated energy usage")
                )
                .servers(
                        List.of(
                                new Server()
                                        .url("http://localhost:9108")
                                        .description("Local DEV server")
                        )
                )
                //To arrange the tags in the order we want
                .tags(List.of(
                        new Tag().name("Energy Usage API").description("Operations for reading aggregated user energy usage")
                ));
    }




    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("api-docs")
                .packagesToScan("com.Home_Energy_Tracker.Energy_Usage_Service.controller") // Specify the package to scan for controllers
                .build();
    }
}
