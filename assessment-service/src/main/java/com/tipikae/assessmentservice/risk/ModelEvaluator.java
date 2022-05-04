/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import com.tipikae.assessmentservice.exception.ValidatorNotFoundException;
import com.tipikae.assessmentservice.model.Patient;

/**
 * Evaluator with a model object.
 * @author tipikae
 * @version 1.0
 *
 */
public class ModelEvaluator extends AbstractEvaluator {
	
	private Object model;

	public ModelEvaluator(Object model) {
		this.model = model;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected AbstractValidator createValidator(String expression) throws ValidatorNotFoundException {
		if(model instanceof Patient) {
			return new ModelPatientValidator(expression, (Patient) model);
		}
		
		throw new ValidatorNotFoundException("validator model for " + model.getClass().getSimpleName() 
				+ " object not found");
	}

}
