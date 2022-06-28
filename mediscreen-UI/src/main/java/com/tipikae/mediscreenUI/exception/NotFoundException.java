/**
 * 
 */
package com.tipikae.mediscreenUI.exception;

/**
 * Patient not found exception.
 * @author tipikae
 * @version 1.0
 *
 */
public class NotFoundException extends ClientException {

	private static final long serialVersionUID = 1L;

	public NotFoundException(String message) {
		super(message);
	}

}
