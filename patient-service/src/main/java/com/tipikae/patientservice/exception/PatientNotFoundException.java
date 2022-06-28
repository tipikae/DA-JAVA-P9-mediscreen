/**
 * 
 */
package com.tipikae.patientservice.exception;

/**
 * Patient not found exception.
 * @author tipikae
 * @version 1.0
 *
 */
public class PatientNotFoundException extends PatientServiceException {

	private static final long serialVersionUID = 1L;

	public PatientNotFoundException(String message) {
		super(message);
	}

}
