/**
 * 
 */
package com.tipikae.assessmentservice.risk.core;

import com.tipikae.assessmentservice.exception.ExpressionValidationException;
import com.tipikae.assessmentservice.exception.ValidatorNotFoundException;

/**
 * Abstract evaluator.
 * @author tipikae
 * @version 1.0
 *
 */
public abstract class AbstractEvaluator implements IEvaluator {
	
	/**
	 * Create validator.
	 * @return Validator
	 * @throws ValidatorNotFoundException 
	 */
	protected abstract AbstractValidator createValidator(String expression) throws ValidatorNotFoundException;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean evaluateExpression(String expression) 
			throws ExpressionValidationException, ValidatorNotFoundException {
		AbstractValidator validator = this.createValidator(expression);
		return validator.valid();
	}

}
