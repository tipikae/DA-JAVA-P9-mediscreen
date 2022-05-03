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
public class CountValidator extends Validator {
	
	private int count;

	public CountValidator(String expression, int count) {
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
