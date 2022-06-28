/**
 * 
 */
package com.tipikae.assessmentservice.exception;

/**
 * Not found exception.
 * @author tipikae
 * @version 1.0
 *
 */
public class NotFoundException extends AssessmentServiceException {

	private static final long serialVersionUID = 1L;

	public NotFoundException(String message) {
		super(message);
	}

}
