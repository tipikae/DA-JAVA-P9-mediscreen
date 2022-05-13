/**
 * 
 */
package com.tipikae.mediscreenUI.config;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for Keycloak adapter.
 * @author tipikae
 * @version 1.0
 *
 */
@Configuration
public class KeycloakAdapterConfig {

	@Bean
	public KeycloakSpringBootConfigResolver springBootConfigResolver() {
		return new KeycloakSpringBootConfigResolver();
	}
}
