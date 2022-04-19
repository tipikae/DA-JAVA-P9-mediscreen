/**
 * 
 */
package com.tipikae.patientservice.exception;

/**
 * Patient already exists exception.
 * @author tipikae
 * @version 1.0
 *
 */
public class PatientAlreadyExistsException extends PatientServiceException {

	private static final long serialVersionUID = 1L;

	public PatientAlreadyExistsException(String message) {
		super(message);
	}

}
