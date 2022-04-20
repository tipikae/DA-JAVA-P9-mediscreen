/**
 * 
 */
package com.tipikae.mediscreenUI.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tipikae.mediscreenUI.client.IPatientServiceClient;
import com.tipikae.mediscreenUI.exception.MyFeignErrorDecoder;

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

	@Value(value = "${proxy.url:}")
	private String proxyUrl;
	
	@Bean
	public IPatientServiceClient getPatientServiceClient() {
		return Feign.builder()
				.encoder(new JacksonEncoder())
				.decoder(new JacksonDecoder())
				.errorDecoder(new MyFeignErrorDecoder())
				.target(IPatientServiceClient.class, proxyUrl + PATIENT_SERVICE_URL);
	}
	
	@Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
