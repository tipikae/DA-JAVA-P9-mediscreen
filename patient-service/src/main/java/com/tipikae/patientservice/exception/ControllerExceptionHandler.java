/**
 * 
 */
package com.tipikae.patientservice.exception;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Controller advice.
 * @author tipikae
 * @version 1.0
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerExceptionHandler.class);
	
	/**
	 * Handle a PatientNotFoundException.
	 * @param e	PatientNotFoundException
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(PatientNotFoundException.class)
	Error exceptionHandler(PatientNotFoundException e) {
		logException(e.getClass().getSimpleName(), HttpStatus.NOT_FOUND.value(), 
				e.getMessage());
		return new Error(HttpStatus.NOT_FOUND.value(), "Patient not found.");
	}
	
	/**
	 * Handle a PatientAlreadyExistsException.
	 * @param e	PatientAlreadyExistsException
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.CONFLICT)
	@ExceptionHandler(PatientAlreadyExistsException.class)
	Error exceptionHandler(PatientAlreadyExistsException e) {
		logException(e.getClass().getSimpleName(), HttpStatus.CONFLICT.value(), 
				e.getMessage());
		return new Error(HttpStatus.CONFLICT.value(), "Patient already exists.");
	}
	
	/**
	 * Handle a MethodArgumentNotValidException.
	 * @param e	MethodArgumentNotValidException
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	Error exceptionHandler(MethodArgumentNotValidException e) {
		logException(e.getClass().getSimpleName(), HttpStatus.BAD_REQUEST.value(), 
				e.getMessage());
		return new Error(HttpStatus.BAD_REQUEST.value(), "Method argument not valid.");
	}
	
	/**
	 * Handle a ConstraintViolationException.
	 * @param e	ConstraintViolationException
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	Error exceptionHandler(ConstraintViolationException e) {
		logException(e.getClass().getSimpleName(), HttpStatus.BAD_REQUEST.value(), 
				e.getMessage());
		return new Error(HttpStatus.BAD_REQUEST.value(), "Validation failed.");
	}
	
	/**
	 * Handle an MissingPathVariableException.
	 * @param e	MissingPathVariableException
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingPathVariableException.class)
	Error exceptionHandler(MissingPathVariableException e) {
		logException(e.getClass().getSimpleName(), HttpStatus.BAD_REQUEST.value(), 
				e.getMessage());
		return new Error(HttpStatus.BAD_REQUEST.value(), "Missing path variable.");
	}
	
	/**
	 * Handle an Exception.
	 * @param e	Exception
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	Error exceptionHandler(Exception e) {
		logException(e.getClass().getSimpleName(), HttpStatus.BAD_REQUEST.value(), 
				e.getMessage());
		return new Error(HttpStatus.BAD_REQUEST.value(), "An exception occured.");
	}

	private void logException(String exception, int code, String message) {
		LOGGER.error("Catching {}, code: {}, message: {}", exception, code, message);
	}
}
