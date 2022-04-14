/**
 * 
 */
package com.tipikae.patientservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.tipikae.patientservice.model.Patient;

/**
 * Configuration for Spring data REST.
 * @author tipikae
 * @version 1.0
 *
 */
@Configuration
public class SpringDataRestConfiguration implements RepositoryRestConfigurer {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		config.exposeIdsFor(Patient.class);
	}

}
