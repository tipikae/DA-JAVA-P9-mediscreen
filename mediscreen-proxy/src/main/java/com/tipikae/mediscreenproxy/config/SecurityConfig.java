/**
 * 
 */
package com.tipikae.mediscreenproxy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Security configuration.
 * @author tipikae
 * @version 1.0
 *
 */
@Configuration
public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
		http.authorizeExchange()
	        .anyExchange()
	        .authenticated()
	        .and()
	        .oauth2Login();
		http.csrf().disable();
		
		return http.build();
	}
}
