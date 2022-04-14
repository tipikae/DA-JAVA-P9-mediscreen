/**
 * 
 */
package com.tipikae.mediscreenUI.exception;

/**
 * Patient already exists exception.
 * @author tipikae
 * @version 1.0
 *
 */
public class PatientAlreadyExistException extends ClientException {

	private static final long serialVersionUID = 1L;

	public PatientAlreadyExistException(String message) {
		super(message);
	}

}
