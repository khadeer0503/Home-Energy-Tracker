package com.Home_Energy_Tracker.User_Service.config;

import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.tags.Tag;
import io.swagger.v3.oas.models.info.Contact;
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
                                .title("Home Energy Tracking System API")
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
                     //   new Tag().name("User Registration").description("User API - Operations related to Home Energy Tracking System [ Sign In, Sign Out]"),
                        new Tag().name("User API- RestController").description("User API - Operations related to Home Energy Tracking System [Create, Read, Update, Delete]")
                        //  new Tag().name("Department API for Registration").description("Department API - Operations related to Department management [ Add, Update, Delete, Get]"),
                        //  new Tag().name("Employee API for Registration").description("Employee API - Operations related to employee management [ Add, Update, Delete, Get]")
                ));
    }




    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("api-docs")
                .packagesToScan("com.Home_Energy_Tracker.User_Service.controller") // Specify the package to scan for controllers
                .build();
    }
}
