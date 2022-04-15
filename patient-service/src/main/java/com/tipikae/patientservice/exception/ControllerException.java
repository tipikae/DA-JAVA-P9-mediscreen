/**
 * 
 */
package com.tipikae.patientservice.exception;

import java.io.Serializable;
import java.util.Date;

/**
 * Controller exception.
 * @author tipikae
 * @version 1.0
 *
 */
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

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
