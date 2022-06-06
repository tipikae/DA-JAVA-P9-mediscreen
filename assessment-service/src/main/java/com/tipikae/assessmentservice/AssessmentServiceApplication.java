package com.tipikae.assessmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@EnableEurekaClient
@SpringBootApplication
@OpenAPIDefinition(info =
	@Info(title = "Assessment Service API", 
		version = "1.0", 
		description = "Documentation Assessment Service API v1.0"
	)
)
public class AssessmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AssessmentServiceApplication.class, args);
	}

}
