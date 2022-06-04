/**
 * 
 */
package com.tipikae.mediscreenUI.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * Custom authentication provider.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyAuthenticationProvider.class);
	
	@Autowired
	private MyAuthentication myAuthentication;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		return myAuthentication.authenticate(authentication);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		LOGGER.debug("supports: class=" + authentication.getClass().getSimpleName());
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
