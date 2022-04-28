/**
 * 
 */
package com.tipikae.assessmentservice.assessment;

import com.tipikae.assessmentservice.exception.AssessmentServiceException;

/**
 * Exception when reading terms storage.
 * @author tipikae
 * @version 1.0
 *
 */
public class TermReaderException extends AssessmentServiceException {

	private static final long serialVersionUID = 1L;

	public TermReaderException(String message) {
		super(message);
	}

}
