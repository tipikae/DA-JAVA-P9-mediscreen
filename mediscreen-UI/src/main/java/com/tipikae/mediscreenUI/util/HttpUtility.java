/**
 * 
 */
package com.tipikae.mediscreenUI.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

/**
 * Utility method for HTTP.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class HttpUtility {
	
	private static final String AUTHORIZATION = "Authorization";
	private static final String BEARER = "Bearer";
	private static final String CONTENT_TYPE = "Content-Type";
	private static final String APPLICATION_JSON = "application/json";
	private static final String APPLICATION_XFORM = "application/x-www-form-urlencoded";
	
	@Autowired
	private SessionUtility sessionUtility;

	/**
	 * Get an HttpEntity object.
	 * @return HttpEntity
	 */
	public HttpEntity<Void> getHttpEntity() {
		return new HttpEntity<>(getAuthHeadersJson());
	}
	
	/**
	 * Get an HttpHeaders object with content-type to JSON.
	 * @return HttpHeaders
	 */
	public HttpHeaders getAuthHeadersJson() {
		HttpHeaders headers = new HttpHeaders();
		headers.add(AUTHORIZATION, BEARER + " " + sessionUtility.getAccessToken());
		headers.add(CONTENT_TYPE, APPLICATION_JSON);
		
		return headers;
	}
	
	/**
	 * Get an HttpHeaders object with content-type to XFORM.
	 * @return HttpHeaders
	 */
	public HttpHeaders getAuthHeadersXForm() {
		HttpHeaders headers = new HttpHeaders();
		headers.add(AUTHORIZATION, BEARER + " " + sessionUtility.getAccessToken());
		headers.add(CONTENT_TYPE, APPLICATION_XFORM);
		
		return headers;
	}
}
