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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpEntity;
import org.springframework.security.authentication.AuthenticationProvider;
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

/**
 * Custom authentication provider.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class MyAuthenticationProvider implements AuthenticationProvider {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyAuthenticationProvider.class);
	
	@Value("${keycloak.endpoint.token:http://localhost:8070/realms/mediscreen/protocol/openid-connect/token}")
	private String tokenEndpoint;
	
	@Value("${keycloak.client_id:mediscreen-proxy}")
	private String clientId;
	
	@Value("${keycloak.client_secret:dXQz51i8e0Ae8iCCzdHFb4aLr0sBhL0T}")
	private String clientSecret;
	
	@Value("${keycloak.grant_type:password}")
	private String grantType;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		LOGGER.debug("authenticate: username=" + username + ", password=" + password);
		
		if(!username.isBlank() && !password.isBlank()) {
    		ServletRequestAttributes attr = (ServletRequestAttributes) 
    			    RequestContextHolder.currentRequestAttributes();
			HttpSession session= attr.getRequest().getSession(true);
			RestTemplate restTemplate = new RestTemplate();
			String url = tokenEndpoint;
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("username", username);
			map.add("password", password);
			map.add("grant_type", grantType);
			map.add("client_id", clientId);
			map.add("client_secret", clientSecret);
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, null);
			
			try {
				String response = restTemplate.postForObject(url, entity, String.class);
				LOGGER.debug("authenticate: response=" + response);
				JsonParser springParser = JsonParserFactory.getJsonParser();
			    Map<String, Object> parsedResponse = springParser.parseMap(response);
			    
			    if(parsedResponse.get("access_token") != null 
			    		&& parsedResponse.get("refresh_token") != null) {
			    	String accessToken = (String) parsedResponse.get("access_token");
			    	String refreshToken = (String) parsedResponse.get("refresh_token");
			    	LOGGER.debug("authenticate: access_token=" + accessToken);
			    	LOGGER.debug("authenticate: refresh_token=" + refreshToken);
			    	DecodedJWT jwt = JWT.decode(accessToken);
			    	List<String> roles = 
			    			((List)jwt.getClaim("realm_access").asMap().get("roles"));
			    	if(roles.contains("USER")) {
			    		LOGGER.debug("authenticate: authenticated");
		    			session.setAttribute("access_token", accessToken);
		    			session.setAttribute("refresh_token", refreshToken);
			    		
			    		return new UsernamePasswordAuthenticationToken(
			    				username, password, new ArrayList<>());
			    	}
			    	
			    	LOGGER.debug("authenticate: no USER role: " + roles.toString());
			    	return null;
			    }
				
			    LOGGER.debug("authenticate: no access_token returned");
			    return null;
			} catch (RestClientException e) {
				LOGGER.debug("authenticate: RestClientException: " + e.getMessage());
				return null;
			}
		}
		
		LOGGER.debug("authenticate: username or password is blank");
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		LOGGER.debug("supports: class=" + authentication.getClass().getSimpleName());
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
