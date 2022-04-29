/**
 * 
 */
package com.tipikae.assessmentservice.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * Acessing terms property.
 * @author tipikae
 * @version 1.0
 *
 */
@Configuration
@ConfigurationProperties(prefix = "terms")
@Data
public class TermsProperties {

	private String path;
	
	
}
