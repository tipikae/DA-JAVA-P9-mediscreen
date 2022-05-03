/**
 * 
 */
package com.tipikae.assessmentservice.risk;

/**
 * Evaluator with a method.
 * @author tipikae
 * @version 1.0
 *
 */
public class MethodEvaluator extends AbstractEvaluator {
	
	private int count;

	public MethodEvaluator(int count) {
		this.count = count;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AbstractValidator createValidator(String expression) {
		return new MethodCountValidator(expression, count);
	}

}
