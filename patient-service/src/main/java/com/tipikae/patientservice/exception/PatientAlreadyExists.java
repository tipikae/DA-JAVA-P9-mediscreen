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
public class PatientAlreadyExists extends PatientServiceException {

	private static final long serialVersionUID = 1L;

	public PatientAlreadyExists(String message) {
		super(message);
	}

}
