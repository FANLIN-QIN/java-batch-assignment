package com.example.details;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import springfox.documentation.oas.annotations.EnableOpenApi;



@SpringBootApplication
@EnableEurekaClient
@EnableOpenApi
public class DetailsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DetailsApplication.class, args);
    }
}
