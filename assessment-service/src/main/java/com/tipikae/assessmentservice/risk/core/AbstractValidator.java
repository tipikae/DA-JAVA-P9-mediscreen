/**
 * 
 */
package com.tipikae.assessmentservice.risk.core;

import com.tipikae.assessmentservice.exception.ExpressionValidationException;
import com.tipikae.assessmentservice.exception.ValidatorNotFoundException;

/**
 * Expression validator.
 * @author tipikae
 * @version 1.0
 *
 */
public abstract class AbstractValidator {
	
	protected String expression;

	public AbstractValidator(String expression) {
		this.expression = expression;
	}

	/**
	 * Check if an expression is true or false.
	 * @return boolean
	 * @throws ExpressionValidationException
	 * @throws ValidatorNotFoundException 
	 */
	public abstract boolean valid() throws ExpressionValidationException, ValidatorNotFoundException;
}
