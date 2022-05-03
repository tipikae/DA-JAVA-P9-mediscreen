/**
 * 
 */
package com.tipikae.assessmentservice.risk;

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
	 * Validate or not an expression.
	 * @return boolean
	 */
	public abstract boolean valid();
}
