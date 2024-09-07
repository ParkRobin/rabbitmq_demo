package com.robin.rabbitmq;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(
                title = "RabbitMQ Demo API",
                version = "1.0",
                description = "RabbitMQ Demo project",
                contact = @Contact(name = "Robin Park", url = "https://github.com/parkrobin", email = "lpiao@ncsu.edu")
        )
)
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}
