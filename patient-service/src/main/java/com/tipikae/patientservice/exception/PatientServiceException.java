/**
 * 
 */
package com.tipikae.patientservice.exception;

/**
 * Parent exception for all application exceptions.
 * @author tipikae
 * @version 1.0
 *
 */
public class PatientServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public PatientServiceException(String message) {
		super(message);
	}

}
