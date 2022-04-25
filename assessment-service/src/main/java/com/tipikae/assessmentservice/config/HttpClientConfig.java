package com.tipikae.assessmentservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.tipikae.assessmentservice.client.INoteServiceClient;
import com.tipikae.assessmentservice.client.IPatientServiceClient;
import com.tipikae.assessmentservice.errorDecoder.NoteServiceErrorDecoder;
import com.tipikae.assessmentservice.errorDecoder.PatientServiceErrorDecoder;

import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;

/**
 * Configuration for Feign clients.
 * @author tipikae
 * @version 1.0
 *
 */
@Configuration
public class HttpClientConfig {

	private static final String PATIENT_SERVICE = "PATIENT-SERVICE";
	private static final String NOTE_SERVICE = "NOTE-SERVICE";
	
	@Autowired
	private EurekaClient discoveryClient;
	
	@Bean
	public IPatientServiceClient getPatientServiceClient() {
		return Feign.builder()
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.errorDecoder(new PatientServiceErrorDecoder())
				.target(IPatientServiceClient.class, serviceUrl(PATIENT_SERVICE));
	}
	
	@Bean
	public INoteServiceClient getNoteServiceClient() {
		return Feign.builder()
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.errorDecoder(new NoteServiceErrorDecoder())
				.target(INoteServiceClient.class, serviceUrl(NOTE_SERVICE));
	}
	
	@Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
	
	private String serviceUrl(String service) {
	    InstanceInfo instance = discoveryClient.getNextServerFromEureka(service, false);
	    return instance.getHomePageUrl();
	}

}
