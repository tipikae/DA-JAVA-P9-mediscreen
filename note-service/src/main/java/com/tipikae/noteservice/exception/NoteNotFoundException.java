/**
 * 
 */
package com.tipikae.noteservice.exception;

/**
 * Note not found exception.
 * @author tipikae
 * @version 1.0
 *
 */
public class NoteNotFoundException extends NoteServiceException {

	private static final long serialVersionUID = 1L;

	public NoteNotFoundException(String message) {
		super(message);
	}

}
