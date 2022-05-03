/**
 * 
 */
package com.tipikae.assessmentservice.risk;

/**
 * Validator with model object.
 * @author tipikae
 * @version 1.0
 *
 */
public class PatientValidator extends Validator {
	
	private int age;
	private char sex;

	public PatientValidator(String expression, int age, char sex) {
		super(expression);
		this.age = age;
		this.sex = sex;
	}

	@Override
	public boolean valid() {
		
		return false;
	}

}
