/**
 * 
 */
package com.tipikae.mediscreenUI.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * Security configuration.
 * @author tipikae
 * @version 1.0
 *
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyAuthenticationProvider authenticationProvider;
	
	@Autowired
	private MyAuthenticationFailureHandler authenticationFailureHandler;
	
	@Autowired
	private MyLogoutHandler logoutHandler;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
        	.authorizeRequests()
		    .anyRequest().authenticated()
		    .and()
		    .formLogin().permitAll()
		    .failureHandler(authenticationFailureHandler)
		    .defaultSuccessUrl("/patient/all", true)
		    .failureUrl("/login?error=true")
	        .and()
	        .logout()
	        .addLogoutHandler(logoutHandler)
	        .logoutSuccessUrl("/patient/all")
	        .deleteCookies("JSESSIONID");
    }
	
	/**
	 * Get HttpSessionEventPublisher bean.
	 * @return HttpSessionEventPublisher
	 */
	@Bean
	public HttpSessionEventPublisher httpSessionEventPublisher() {
	    return new HttpSessionEventPublisher();
	}

}
