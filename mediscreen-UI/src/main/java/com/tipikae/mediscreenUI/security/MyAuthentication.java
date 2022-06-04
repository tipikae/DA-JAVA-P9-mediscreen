/**
 * 
 */
package com.tipikae.mediscreenUI.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.tipikae.mediscreenUI.util.SessionUtility;

/**
 * My custom authentication.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class MyAuthentication {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyAuthentication.class);
	private static final String CLAIM_ROLES = "roles";
	private static final String ROLE_USER = "ROLE_USER";
	
	@Autowired
	private SessionUtility sessionUtility;
	
	@Autowired
	private MyLogout authenticationLogout;
	
	@Value("${keycloak.endpoint.token:}")
	private String tokenEndpoint;
	
	@Value("${keycloak.client_id:}")
	private String clientId;
	
	@Value("${keycloak.grant_type:}")
	private String grantType;

	/**
	 * Authenticate a user.
	 * @param authentication Authentication
	 * @return Authentication
	 * @throws AuthenticationException
	 */
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		LOGGER.debug("authenticate: username=" + username + ", password=" + password);
		
		// check if credentials are valid
		if(!username.isBlank() && !password.isBlank()) {
    		ServletRequestAttributes attr = 
    				(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
			HttpSession session= attr.getRequest().getSession(true);
			
			// create a restTemplate request for login in from keycloak
			RestTemplate restTemplate = new RestTemplate();
			String url = tokenEndpoint;
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("username", username);
			map.add("password", password);
			map.add("grant_type", grantType);
			map.add("client_id", clientId);
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, null);
			
			try {
				// call keycloak
				String response = restTemplate.postForObject(url, entity, String.class);
				LOGGER.debug("authenticate: response=" + response);
				
				// parse the response for getting tokens
				JsonParser springParser = JsonParserFactory.getJsonParser();
			    Map<String, Object> parsedResponse = springParser.parseMap(response);
			    
			    if(parsedResponse.get(sessionUtility.getAccessTokenKey()) != null 
			    		&& parsedResponse.get(sessionUtility.getRefreshTokenKey()) != null) {
			    	// get tokens
			    	String accessToken = (String) parsedResponse.get(sessionUtility.getAccessTokenKey());
			    	String refreshToken = (String) parsedResponse.get(sessionUtility.getRefreshTokenKey());
			    	LOGGER.debug("authenticate: access_token=" + accessToken);
			    	LOGGER.debug("authenticate: refresh_token=" + refreshToken);
			    	
			    	// save tokens
	    			session.setAttribute(sessionUtility.getAccessTokenKey(), accessToken);
	    			session.setAttribute(sessionUtility.getRefreshTokenKey(), refreshToken);
			    	
	    			// get roles and check if right role is present
			    	DecodedJWT jwt = JWT.decode(accessToken);
			    	if(jwt.getClaims().get(CLAIM_ROLES) != null 
			    			&& !jwt.getClaims().get(CLAIM_ROLES).isNull()) {
				    	List<String> roles = 
				    			(List<String>)jwt.getClaims().get(CLAIM_ROLES).asList(String.class);
				    	if(roles.contains(ROLE_USER)) {
				    		LOGGER.debug("authenticate: authenticated");
				    		
				    		return new UsernamePasswordAuthenticationToken(
				    				username, password, new ArrayList<>());
				    	}
			    	}
			    	
			    	// failure: call logout
			    	LOGGER.debug("authenticate: no ROLE_USER role, calling logout");
			    	authenticationLogout.logout();
			    	throw new BadCredentialsException("Unauthorized access.");
			    }
				
			    LOGGER.debug("authenticate: no access_token returned");
			    throw new AuthenticationServiceException("An error occured.");
			} catch (RestClientException e) {
				LOGGER.debug("authenticate: RestClientException: " + e.getMessage());
			} catch(JsonParseException e) {
				LOGGER.debug("authenticate: JsonParseException: " + e.getMessage());
			}
			
			throw new AuthenticationServiceException("Service error.");
		}
		
		LOGGER.debug("authenticate: username or password is blank");
		throw new BadCredentialsException("Incorrect credentials");
	}
}
