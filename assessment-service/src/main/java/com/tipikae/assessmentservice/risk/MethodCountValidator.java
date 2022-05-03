/**
 * 
 */
package com.tipikae.assessmentservice.risk;

/**
 * Validator with method.
 * @author tipikae
 * @version 1.0
 *
 */
public class MethodCountValidator extends AbstractValidator {
	
	private int count;

	public MethodCountValidator(String expression, int count) {
		super(expression);
		this.count = count;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean valid() {
		
		return false;
	}

}
