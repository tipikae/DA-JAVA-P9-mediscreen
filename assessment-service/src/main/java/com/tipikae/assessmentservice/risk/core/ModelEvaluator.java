/**
 * 
 */
package com.tipikae.assessmentservice.risk.core;

import com.tipikae.assessmentservice.exception.ValidatorNotFoundException;

/**
 * Evaluator with a model object.
 * @author tipikae
 * @version 1.0
 *
 */
public class ModelEvaluator extends AbstractEvaluator {
	
	private Object object;

	public ModelEvaluator(Object object) {
		this.object = object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AbstractValidator createValidator(String expression) throws ValidatorNotFoundException {
		return new ModelValidator(expression, object);
	}

}
