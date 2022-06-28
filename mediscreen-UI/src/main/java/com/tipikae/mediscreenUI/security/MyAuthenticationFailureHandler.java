/**
 * 
 */
package com.tipikae.mediscreenUI.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Custom authentication failure handler.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MyAuthenticationFailureHandler.class);
 
    private ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		LOGGER.debug("onAuthenticationFailure: " + exception.getMessage());
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Map<String, Object> data = new HashMap<>();
        data.put(
          "exception", 
          exception.getMessage());

        response.getOutputStream()
          .println(objectMapper.writeValueAsString(data));
	}

}
