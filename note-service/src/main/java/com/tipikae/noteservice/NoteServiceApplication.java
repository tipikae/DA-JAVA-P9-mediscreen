package com.tipikae.noteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import io.mongock.runner.springboot.EnableMongock;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;


@EnableMongock
@EnableEurekaClient
@SpringBootApplication
@OpenAPIDefinition(info =
	@Info(title = "Note Service API", 
		version = "1.0", 
		description = "Documentation Note Service API v1.0"
	)
)
public class NoteServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(NoteServiceApplication.class, args);
	}

}
