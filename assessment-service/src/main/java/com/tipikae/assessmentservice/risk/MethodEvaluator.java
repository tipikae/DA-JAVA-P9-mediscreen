/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import com.tipikae.assessmentservice.exception.ValidatorNotFoundException;

/**
 * Evaluator with a method.
 * @author tipikae
 * @version 1.0
 *
 */
public class MethodEvaluator extends AbstractEvaluator {
	
	private Object object;

	public MethodEvaluator(Object object) {
		this.object = object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AbstractValidator createValidator(String expression) throws ValidatorNotFoundException {
		return new MethodValidator(expression, object);
	}

}
