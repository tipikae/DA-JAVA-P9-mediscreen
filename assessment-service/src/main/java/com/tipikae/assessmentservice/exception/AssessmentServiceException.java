/**
 * 
 */
package com.tipikae.assessmentservice.exception;

/**
 * Parent exception for all application's exceptions.
 * @author tipikae
 * @version 1.0
 *
 */
public class AssessmentServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public AssessmentServiceException(String message) {
		super(message);
	}

}
