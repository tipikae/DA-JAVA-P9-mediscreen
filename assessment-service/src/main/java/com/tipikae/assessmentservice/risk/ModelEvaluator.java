/**
 * 
 */
package com.tipikae.assessmentservice.risk;

/**
 * Evaluator with a model object.
 * @author tipikae
 * @version 1.0
 *
 */
public class ModelEvaluator extends Evaluator {
	
	private int age;
	private char sex;

	public ModelEvaluator(int age, char sex) {
		this.age = age;
		this.sex = sex;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected Validator createValidator(String expression) {
		return new PatientValidator(expression, age, sex);
	}

}
