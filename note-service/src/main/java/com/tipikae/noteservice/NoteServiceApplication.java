package com.tipikae.noteservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import io.mongock.runner.springboot.EnableMongock;


@EnableMongock
@EnableEurekaClient
@SpringBootApplication
public class NoteServiceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(NoteServiceApplication.class, args);
	}

}
