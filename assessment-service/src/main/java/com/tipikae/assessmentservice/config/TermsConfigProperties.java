/**
 * 
 */
package com.tipikae.assessmentservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * Acessing terms property.
 * @author tipikae
 * @version 1.0
 *
 */
@ConfigurationProperties(prefix = "terms")
@Data
public class TermsConfigProperties {

	private String path;
	
	
}
