package com.tipikae.assessmentservice.exception;

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
	ControllerException exceptionHandler(PatientNotFoundException e) {
		logException(e.getClass().getSimpleName(), HttpStatus.NOT_FOUND.value(), 
				e.getMessage());
		return new ControllerException(HttpStatus.NOT_FOUND.value(), "Patient not found.");
	}
	
	/**
	 * Handle a MethodArgumentNotValidException.
	 * @param e	MethodArgumentNotValidException
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	ControllerException exceptionHandler(MethodArgumentNotValidException e) {
		logException(e.getClass().getSimpleName(), HttpStatus.BAD_REQUEST.value(), 
				e.getMessage());
		return new ControllerException(HttpStatus.BAD_REQUEST.value(), "Method argument not valid.");
	}
	
	/**
	 * Handle a ConstraintViolationException.
	 * @param e	ConstraintViolationException
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	ControllerException exceptionHandler(ConstraintViolationException e) {
		logException(e.getClass().getSimpleName(), HttpStatus.BAD_REQUEST.value(), 
				e.getMessage());
		return new ControllerException(HttpStatus.BAD_REQUEST.value(), "Validation failed.");
	}
	
	/**
	 * Handle an MissingPathVariableException.
	 * @param e	MissingPathVariableException
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MissingPathVariableException.class)
	ControllerException exceptionHandler(MissingPathVariableException e) {
		logException(e.getClass().getSimpleName(), HttpStatus.BAD_REQUEST.value(), 
				e.getMessage());
		return new ControllerException(HttpStatus.BAD_REQUEST.value(), "Missing path variable.");
	}
	
	/**
	 * Handle an Exception.
	 * @param e	Exception
	 * @return ControllerException
	 */
	@ResponseBody
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(Exception.class)
	ControllerException exceptionHandler(Exception e) {
		logException(e.getClass().getSimpleName(), HttpStatus.BAD_REQUEST.value(), 
				e.getMessage());
		return new ControllerException(HttpStatus.BAD_REQUEST.value(), "An exception occured.");
	}

	private void logException(String exception, int code, String message) {
		LOGGER.error("Catching {}, code: {}, message: {}", exception, code, message);
	}
}
