/**
 * 
 */
package com.tipikae.mediscreenUI.exception;

/**
 * Bad request exception.
 * @author tipikae
 * @version 1.0
 *
 */
public class BadRequestException extends ClientException {

	private static final long serialVersionUID = 1L;

	public BadRequestException(String message) {
		super(message);
	}

}
