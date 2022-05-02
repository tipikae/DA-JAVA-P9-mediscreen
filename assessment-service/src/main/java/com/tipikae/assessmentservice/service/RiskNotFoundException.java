/**
 * 
 */
package com.tipikae.assessmentservice.service;

import com.tipikae.assessmentservice.exception.AssessmentServiceException;

/**
 * Risk not found exception.
 * @author tipikae
 * @version 1.0
 *
 */
public class RiskNotFoundException extends AssessmentServiceException {

	private static final long serialVersionUID = 1L;

	public RiskNotFoundException(String message) {
		super(message);
	}

}
