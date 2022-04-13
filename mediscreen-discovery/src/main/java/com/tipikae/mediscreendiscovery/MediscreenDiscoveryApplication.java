package com.tipikae.mediscreendiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MediscreenDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(MediscreenDiscoveryApplication.class, args);
	}

}
