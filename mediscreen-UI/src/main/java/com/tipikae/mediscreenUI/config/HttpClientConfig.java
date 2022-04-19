/**
 * 
 */
package com.tipikae.mediscreenUI.config;

import java.util.Arrays;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.mediatype.hal.Jackson2HalModule;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tipikae.mediscreenUI.client.IPatientServiceClient;
import com.tipikae.mediscreenUI.exception.MyFeignErrorDecoder;

import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.jackson.JacksonEncoder;

/**
 * Configuration for Feign clients.
 * @author tipikae
 * @version 1.0
 *
 */
@Configuration
@EnableHypermediaSupport(type = EnableHypermediaSupport.HypermediaType.HAL)
public class HttpClientConfig {
	
	private static final String PATIENT_SERVICE_URL = "/patient-service";

	@Value(value = "${proxy.url:}")
	private String proxyUrl;
	
	@Bean
	public IPatientServiceClient getPatientServiceClient() {
		return Feign.builder()
				.encoder(new JacksonEncoder())
				.decoder(feignDecoder())
				.errorDecoder(new MyFeignErrorDecoder())
				.target(IPatientServiceClient.class, proxyUrl + PATIENT_SERVICE_URL);
	}
	
	@Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
	
	@Bean
	public Decoder feignDecoder() {
		ObjectMapper objectMapper = new ObjectMapper()
				 .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
				 .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true)
				 .registerModule(new Jackson2HalModule());
		
		MappingJackson2HttpMessageConverter jacksonConverter = new MappingJackson2HttpMessageConverter();
		jacksonConverter.setSupportedMediaTypes(Arrays.asList(MediaTypes.HAL_JSON));
		jacksonConverter.setObjectMapper(objectMapper);
	    
	    ObjectFactory<HttpMessageConverters> objectFactory = () -> new HttpMessageConverters(jacksonConverter);
        return new ResponseEntityDecoder(new SpringDecoder(objectFactory));
	}
}
