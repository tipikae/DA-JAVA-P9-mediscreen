/**
 * 
 */
package com.tipikae.mediscreenUI.security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Logout handler.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class MyLogoutHandler implements LogoutHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyLogoutHandler.class);
	
	@Value("${keycloak.endpoint.logout:http://localhost:8070/realms/mediscreen/protocol/openid-connect/logout}")
	private String logoutEndpoint;
	
	@Value("${keycloak.client_id:mediscreen-proxy}")
	private String clientId;
	
	@Value("${keycloak.client_secret:PWeoya0glZUlqsPi190Ke0EjlBPvu4pA}")
	private String clientSecret;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, 
			Authentication authentication) {
		LOGGER.debug("logout: username=" + authentication.getName());
		/*try {
			request.logout();
		} catch (ServletException e) {
			LOGGER.debug("logout: failed to logout HttpServletRequest: " + e.getMessage());
		}*/
		
		RestTemplate restTemplate = new RestTemplate();
		String url = logoutEndpoint;
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("client_id", clientId);
		map.add("client_secret", clientSecret);
		//map.add("refresh_token", url);
		
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, null);
		restTemplate.postForObject(url, entity, String.class);
	}

}
