/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import com.tipikae.assessmentservice.exception.ExpressionValidationException;

/**
 * Expression validator.
 * @author tipikae
 * @version 1.0
 *
 */
public abstract class Validator {
	
	protected String expression;

	public Validator(String expression) {
		this.expression = expression;
	}

	/**
	 * Check if an expression is true or false.
	 * @return boolean
	 * @throws ExpressionValidationException
	 */
	public abstract boolean valid() throws ExpressionValidationException;
}
