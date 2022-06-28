/**
 * 
 */
package com.tipikae.noteservice.exception;

/**
 * Parent exception for all application's exceptions.
 * @author tipikae
 * @version 1.0
 *
 */
public class NoteServiceException extends Exception {

	private static final long serialVersionUID = 1L;

	public NoteServiceException(String message) {
		super(message);
	}

}
