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
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

/**
 * Configuration for Feign clients.
 * @author tipikae
 * @version 1.0
 *
 */
@Configuration
public class HttpClientConfig {

	@Value(value = "${client.patientService.url:}")
	private String patientServiceUrl;
	
	@Bean
	public IPatientServiceClient getPatientServiceClient() {
		return Feign.builder()
				.encoder(new GsonEncoder())
				.decoder(new GsonDecoder())
				.errorDecoder(new MyFeignErrorDecoder())
				.target(IPatientServiceClient.class, patientServiceUrl);
	}
}
