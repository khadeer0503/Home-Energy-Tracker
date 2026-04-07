package com.Home_Energy_Tracker.Device_Service.config;

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
                                .title("Home Energy Tracking System API - Device Service API")
                                .version("1.0")
                                .contact(new Contact().email("mohammed.khadeeruddin@ctlup.com"))
                                .description("API for Home Energy Tracking System")
                )
                .servers(
                        List.of(
                                new Server()
                                        .url("http://localhost:9105")
                                        .description("Local- DEV_Server"),
                                new Server()
                                        .url("http://localhost:9105")
                                        .description("Published- Production_Server")
                        )
                )
                //To arrange the tags in the order we want
                .tags(List.of(
                        new Tag().name("Device Service API- RestController").description("Device Service API - Operations related to Home Energy Tracking System [Create, Read, Update, Delete]")
                ));
    }




    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("api-docs")
                .packagesToScan("com.Home_Energy_Tracker.Device_Service.controller") // Specify the package to scan for controllers
                .build();
    }
}
