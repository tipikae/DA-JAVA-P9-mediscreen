/**
 * 
 */
package com.tipikae.mediscreenUI.security;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
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
		HttpSession session = request.getSession();
		String accessToken = (String) session.getAttribute("access_token");
		String refreshToken = (String) session.getAttribute("refresh_token");
		
		LOGGER.debug("logout: access_token=" + accessToken);
		LOGGER.debug("logout: refresh_token=" + refreshToken);
		
		RestTemplate restTemplate = new RestTemplate();
		String url = logoutEndpoint;
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("client_id", clientId);
		map.add("client_secret", clientSecret);
		map.add("refresh_token", refreshToken);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + accessToken);
		
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
		try {
			String resp = restTemplate.postForObject(url, entity, String.class);
			LOGGER.debug("logout: resp=" + resp);
		} catch (RestClientException e) {
			LOGGER.debug("logout: RestClientException: " + e.getMessage());
		}
	}

}
