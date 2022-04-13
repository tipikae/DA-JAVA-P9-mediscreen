package com.tipikae.mediscreenproxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MediscreenProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediscreenProxyApplication.class, args);
	}

}
