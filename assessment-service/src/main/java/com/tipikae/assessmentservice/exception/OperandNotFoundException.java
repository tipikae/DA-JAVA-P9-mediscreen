/**
 * 
 */
package com.tipikae.assessmentservice.exception;

/**
 * Operand not found exception.
 * @author tipikae
 * @version 1.0
 *
 */
public class OperandNotFoundException extends AssessmentServiceException {

	private static final long serialVersionUID = 1L;

	public OperandNotFoundException(String message) {
		super(message);
	}

}
