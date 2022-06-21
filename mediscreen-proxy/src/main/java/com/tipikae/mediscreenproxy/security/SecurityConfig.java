/**
 * 
 */
package com.tipikae.mediscreenproxy.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;

import reactor.core.publisher.Mono;

/**
 * Security configuration.
 * @author tipikae
 * @version 1.0
 *
 */
@EnableWebFluxSecurity
public class SecurityConfig {
	
	private static final String CLAIM_ROLES = "roles";
	
	@Value("${spring.security.user.name:}")
	private String username;
	
	@Value("${spring.security.user.password:}")
	private String password;
	
	/**
	 * FilterChain bean.
	 * @param http ServerHttpSecurity
	 * @return SecurityWebFilterChain
	 */
	@Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) {
	     http
	     	.authorizeExchange(exchanges -> 
	        		exchanges
	        			.pathMatchers("/webjars/**", "/v3/api-docs/**", "/*/v3/api-docs").permitAll()
	        			.pathMatchers("/patient-service/**", "/note-service/**", "/assessment-service/**")
	        				.hasRole("USER"))
	        .oauth2ResourceServer(oauth2ResourceServer ->
	        	oauth2ResourceServer.jwt(jwt ->
	        			jwt.jwtAuthenticationConverter(grantedAuthoritiesExtractor())));
	     
	     http.authorizeExchange(
	    		 exchanges ->
	    		 	exchanges
	    		 		.pathMatchers("/actuator/**")
	    		 		.hasRole("ADMIN")
	    		 		.anyExchange()
	    		 		.authenticated()
	    		 		.and()
	    		 		.httpBasic()
	    		 	);
	   
		return http.build();
	}
	
	/**
	 * UserDetailsService for in-memory user.
	 * @return MapReactiveUserDetailsService
	 */
	@SuppressWarnings("deprecation")
	@Bean
	public MapReactiveUserDetailsService userDetailsService() {
	    UserDetails user =
	        User.withDefaultPasswordEncoder()
	            .username(username)
	            .password(password)
	            .roles("ADMIN")
	            .build();
	    return new MapReactiveUserDetailsService(user);
	}

	// Extracts manually roles.
	private Converter<Jwt, Mono<AbstractAuthenticationToken>> grantedAuthoritiesExtractor() {
	    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
	    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(new GrantedAuthoritiesExtractor());
	    
	    return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
	}
	
	/**
	 * Granted Authorities extractor.
	 * @author tipikae
	 * @version 1.0
	 *
	 */
	class GrantedAuthoritiesExtractor implements Converter<Jwt, Collection<GrantedAuthority>> {

		/**
		 * {@inheritDoc}
		 */
		@SuppressWarnings("unchecked")
		@Override
		public Collection<GrantedAuthority> convert(Jwt jwt) {
			Collection<String> authorities = (Collection<String>) jwt.getClaims().get(CLAIM_ROLES);
		
		    return authorities.stream()
		            .map(SimpleGrantedAuthority::new)
		            .collect(Collectors.toList());
		}
	}
}
