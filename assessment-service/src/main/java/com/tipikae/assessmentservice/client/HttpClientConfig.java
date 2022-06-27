package com.tipikae.assessmentservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

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
	
	/**
	 * Get PatientService client.
	 * @return IPatientServiceClient
	 */
	@Bean
	public IPatientServiceClient getPatientServiceClient() {
		return Feign.builder()
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.errorDecoder(new PatientServiceErrorDecoder())
				.target(IPatientServiceClient.class, serviceUrl(PATIENT_SERVICE));
	}
	
	/**
	 * Get NoteService client.
	 * @return INoteServiceClient
	 */
	@Bean
	public INoteServiceClient getNoteServiceClient() {
		return Feign.builder()
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.errorDecoder(new NoteServiceErrorDecoder())
				.target(INoteServiceClient.class, serviceUrl(NOTE_SERVICE));
	}
	
	/**
	 * Set Feign log level.
	 * @return Logger.Level
	 */
	@Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
	
	private String serviceUrl(String service) {
	    InstanceInfo instance = discoveryClient.getNextServerFromEureka(service, false);
	    return instance.getHomePageUrl();
	}

}
