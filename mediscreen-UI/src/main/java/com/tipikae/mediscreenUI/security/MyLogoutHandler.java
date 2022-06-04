/**
 * 
 */
package com.tipikae.mediscreenUI.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

/**
 * Logout handler.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class MyLogoutHandler implements LogoutHandler {
	
	@Autowired
	private MyLogout authenticationLogout;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, 
			Authentication authentication) {
		authenticationLogout.logout(request, response, authentication);
	}
}
