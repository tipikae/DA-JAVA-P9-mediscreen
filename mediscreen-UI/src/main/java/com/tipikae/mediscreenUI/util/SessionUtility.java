/**
 * 
 */
package com.tipikae.mediscreenUI.util;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Utility for session.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class SessionUtility {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SessionUtility.class);
	
	/**
	 * Access token key value.
	 */
	public static final String KEY_ACCESS_TOKEN = "access_token";
	
	/**
	 * Refresh token key value.
	 */
	public static final String KEY_REFRESH_TOKEN = "refresh_token";

	/**
	 * Get access token from current session.
	 * @return String
	 */
	public String getAccessToken() {
		ServletRequestAttributes attr = 
				(ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session= attr.getRequest().getSession(false);
		
		if(session != null && session.getAttribute(KEY_ACCESS_TOKEN) != null) {
			LOGGER.debug("getAccessToken: session and token exist");
			return (String) session.getAttribute(KEY_ACCESS_TOKEN);
		}

		LOGGER.debug("getAccessToken: session not exists");
		return null;
	}
	
	/**
	 * Get access_token key.
	 * @return String
	 */
	public String getAccessTokenKey() {
		return KEY_ACCESS_TOKEN;
	}
	
	/**
	 * Get refresh_token key.
	 * @return String
	 */
	public String getRefreshTokenKey() {
		return KEY_REFRESH_TOKEN;
	}
}
