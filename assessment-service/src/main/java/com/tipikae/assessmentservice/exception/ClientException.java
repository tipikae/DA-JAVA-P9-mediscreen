/**
 * 
 */
package com.tipikae.assessmentservice.exception;

/**
 * Feign client exception.
 * @author tipikae
 * @version 1.0
 *
 */
public class ClientException extends AssessmentServiceException {

	private static final long serialVersionUID = 1L;

	public ClientException(String message) {
		super(message);
	}

}
