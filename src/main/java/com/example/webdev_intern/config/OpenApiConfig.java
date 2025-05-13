package com.example.webdev_intern.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI defineOpenApi() {
        Server server = new Server();
        server.setUrl("http://34.87.113.216:8080");
        server.setDescription("Development");

        Contact myContact = new Contact();
        myContact.setName("Tran Chinh Bach");
        myContact.setEmail("bachchinhtran@gmail.com");

        Info information = new Info()
                .title("WebDev Intern Project")
                .version("1.0")
                .description("This is the API documentation for the frontend to work with.")
                .contact(myContact);

        return new OpenAPI()
                .info(information)
                .servers(List.of(server))
                .components(new io.swagger.v3.oas.models.Components());
    }
}
