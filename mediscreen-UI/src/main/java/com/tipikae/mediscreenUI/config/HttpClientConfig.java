/**
 * 
 */
package com.tipikae.mediscreenUI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.support.PageJacksonModule;
import org.springframework.cloud.openfeign.support.SortJacksonModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tipikae.mediscreenUI.client.IAssessmentServiceClient;
import com.tipikae.mediscreenUI.client.INoteServiceClient;
import com.tipikae.mediscreenUI.client.IPatientServiceClient;
import com.tipikae.mediscreenUI.errorDecoder.AssessmentServiceErrorDecoder;
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
	private static final String ASSESSMENT_SERVICE_URL = "/assessment-service";

	@Value(value = "${proxy.url:}")
	private String proxyUrl;
	
	@Bean
	public IPatientServiceClient getPatientServiceClient() {
		return Feign.builder()
				.encoder(new JacksonEncoder(provideObjectMapper()))
				.decoder(new JacksonDecoder(provideObjectMapper()))
				.errorDecoder(new PatientServiceErrorDecoder())
				.logLevel(Logger.Level.FULL)
				.target(IPatientServiceClient.class, proxyUrl + PATIENT_SERVICE_URL);
	}
	
	@Bean
	public INoteServiceClient getNoteServiceClient() {
		return Feign.builder()
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.errorDecoder(new NoteServiceErrorDecoder())
				.logLevel(Logger.Level.FULL)
				.target(INoteServiceClient.class, proxyUrl + NOTE_SERVICE_URL);
	}
	
	@Bean
	public IAssessmentServiceClient getAssessmentServiceClient() {
		return Feign.builder()
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.errorDecoder(new AssessmentServiceErrorDecoder())
				.logLevel(Logger.Level.FULL)
				.target(IAssessmentServiceClient.class, proxyUrl + ASSESSMENT_SERVICE_URL);
	}
	
	private ObjectMapper provideObjectMapper() {
	    return new ObjectMapper()
	        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
	        .registerModule(new PageJacksonModule())
	        .registerModule(new SortJacksonModule());
	  }
}
