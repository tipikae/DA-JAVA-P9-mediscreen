/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import com.tipikae.assessmentservice.exception.ExpressionValidationException;

/**
 * Abstract evaluator.
 * @author tipikae
 * @version 1.0
 *
 */
public abstract class Evaluator implements IEvaluator {
	
	/**
	 * Create validator.
	 * @return Validator
	 */
	protected abstract Validator createValidator(String expression);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean evaluateExpression(String expression) throws ExpressionValidationException {
		Validator validator = this.createValidator(expression);
		return validator.valid();
	}

}
