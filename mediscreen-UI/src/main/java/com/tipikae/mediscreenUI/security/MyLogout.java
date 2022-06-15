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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.tipikae.mediscreenUI.util.HttpUtility;
import com.tipikae.mediscreenUI.util.SessionUtility;

/**
 * Custom authentication logout.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class MyLogout {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyLogout.class);
	
	@Autowired
	private HttpUtility httpUtility;
	
	@Autowired
	private SessionUtility sessionUtility;
	
	@Value("${keycloak.endpoint.logout:}")
	private String logoutEndpoint;
	
	@Value("${keycloak.client_id:}")
	private String clientId;

	/**
	 * Logout from Spring Security and Keycloak.
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @param authentication Authentication
	 */
	public void logout(HttpServletRequest request, HttpServletResponse response, 
			Authentication authentication) {
		String username = authentication == null ? "null" : authentication.getName();
		LOGGER.info("logout: trying to logout username=" + username);
		
		// retrieve tokens from session
		HttpSession session = request.getSession();
		String accessToken = (String) session.getAttribute(sessionUtility.getAccessTokenKey());
		String refreshToken = (String) session.getAttribute(sessionUtility.getRefreshTokenKey());
		LOGGER.debug("logout: access_token=" + accessToken);
		LOGGER.debug("logout: refresh_token=" + refreshToken);
		
		// create a restTemplate request for login out from keycloak
		RestTemplate restTemplate = new RestTemplate();
		String url = logoutEndpoint;
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("client_id", clientId);
		map.add("refresh_token", refreshToken);
		
		HttpEntity<MultiValueMap<String, String>> entity = 
				new HttpEntity<>(map, httpUtility.getAuthHeadersXForm());
		
		try {
			// call keycloak
			String resp = restTemplate.postForObject(url, entity, String.class);
			LOGGER.debug("logout: resp from keycloak=" + resp);
			
			// remove tokens from session
			session.removeAttribute(sessionUtility.getAccessTokenKey());
			session.removeAttribute(sessionUtility.getRefreshTokenKey());
			
			// logout from security
			request.logout();
			LOGGER.info("logout: username=" + username + " is logged out.");
		} catch (RestClientException e) {
			LOGGER.debug("logout: RestClientException: " + e.getMessage());
		} catch (ServletException e) {
			LOGGER.debug("logout: ServletException: " + e.getMessage());
		}
		
	}
	
	/**
	 * Logout from Spring Security and Keycloak.
	 * @param request HttpServletRequest
	 */
	public void logout(HttpServletRequest request) {
		Authentication authentication  = SecurityContextHolder.getContext().getAuthentication();
		logout(request, null, authentication);
	}

	/**
	 * Logout from Spring Security and Keycloak.
	 */
	public void logout() {
		HttpServletRequest request = 
				((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		logout(request);
	}

}
