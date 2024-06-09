package com.example.HelaCane;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "HelaCane API",version = "2.0",description = "test"))
public class HelaCaneApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelaCaneApplication.class, args);
	}

}
