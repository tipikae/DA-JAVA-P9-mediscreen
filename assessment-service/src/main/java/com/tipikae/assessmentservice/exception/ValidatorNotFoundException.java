/**
 * 
 */
package com.tipikae.assessmentservice.exception;

/**
 * Validator not found exception.
 * @author tipikae
 * @version 1.0
 *
 */
public class ValidatorNotFoundException extends AssessmentServiceException {

	private static final long serialVersionUID = 1L;

	public ValidatorNotFoundException(String message) {
		super(message);
	}

}
