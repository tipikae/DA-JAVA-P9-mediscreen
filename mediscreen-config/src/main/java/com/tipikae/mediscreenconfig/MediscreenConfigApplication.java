package com.tipikae.mediscreenconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
@EnableAutoConfiguration
public class MediscreenConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediscreenConfigApplication.class, args);
	}

}
