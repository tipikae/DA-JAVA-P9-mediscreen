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
public class AlreadyExistsException extends ClientException {

	private static final long serialVersionUID = 1L;

	public AlreadyExistsException(String message) {
		super(message);
	}

}
