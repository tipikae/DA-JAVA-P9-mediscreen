package com.tipikae.noteservice.exception;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * Controller exception.
 * @author tipikae
 * @version 1.0
 *
 */
@Data
public class ControllerException implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int code;
	private String message;
	private Date timestamp;
	
	public ControllerException(int code, String message) {
		this.code = code;
		this.message = message;
		this.timestamp = new Date();
	}
}
