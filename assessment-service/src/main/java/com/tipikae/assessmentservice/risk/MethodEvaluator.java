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
public class MethodEvaluator extends Evaluator {
	
	private int count;

	public MethodEvaluator(int count) {
		this.count = count;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Validator createValidator(String expression) {
		return new CountValidator(expression, count);
	}

}
