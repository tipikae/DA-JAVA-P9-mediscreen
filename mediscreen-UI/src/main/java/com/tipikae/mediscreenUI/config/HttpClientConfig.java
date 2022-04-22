/**
 * 
 */
package com.tipikae.mediscreenUI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tipikae.mediscreenUI.client.INoteServiceClient;
import com.tipikae.mediscreenUI.client.IPatientServiceClient;
import com.tipikae.mediscreenUI.errorDecoder.NoteServiceErrorDecoder;
import com.tipikae.mediscreenUI.errorDecoder.PatientServiceErrorDecoder;

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
	
	private static final String PATIENT_SERVICE_URL = "/patient-service";
	private static final String NOTE_SERVICE_URL = "/note-service";

	@Value(value = "${proxy.url:}")
	private String proxyUrl;
	
	@Bean
	public IPatientServiceClient getPatientServiceClient() {
		return Feign.builder()
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.errorDecoder(new PatientServiceErrorDecoder())
				.target(IPatientServiceClient.class, proxyUrl + PATIENT_SERVICE_URL);
	}
	
	@Bean
	public INoteServiceClient getNoteServiceClient() {
		return Feign.builder()
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.errorDecoder(new NoteServiceErrorDecoder())
				.target(INoteServiceClient.class, proxyUrl + NOTE_SERVICE_URL);
	}
	
	@Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
