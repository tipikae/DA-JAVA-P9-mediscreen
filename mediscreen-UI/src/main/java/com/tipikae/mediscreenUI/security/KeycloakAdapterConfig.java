/**
 * 
 */
package com.tipikae.mediscreenUI.security;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.adapters.springsecurity.client.KeycloakClientRequestFactory;
import org.keycloak.adapters.springsecurity.client.KeycloakRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Keycloak adapter configuration
 * @author tipikae
 * @version 1.0
 *
 */
@Configuration
public class KeycloakAdapterConfig {

	/**
	 * KeycloakSpringBootConfigResolver bean.
	 * @return KeycloakSpringBootConfigResolver
	 */
	@Bean
	public KeycloakSpringBootConfigResolver springBootConfigResolver() {
		return new KeycloakSpringBootConfigResolver();
	}
	
	/**
	 * KeycloakRestTemplate bean.
	 * @param keycloakClientRequestFactory
	 * @return
	 */
	@Bean
	public KeycloakRestTemplate keycloakRestTemplate(
			KeycloakClientRequestFactory keycloakClientRequestFactory) {
		return new KeycloakRestTemplate(keycloakClientRequestFactory);
	}
}
