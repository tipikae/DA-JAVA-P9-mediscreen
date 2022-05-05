/**
 * 
 */
package com.tipikae.assessmentservice.risk.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tipikae.assessmentservice.exception.ExpressionValidationException;
import com.tipikae.assessmentservice.exception.ValidatorNotFoundException;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.risk.validator.IModelValidator;
import com.tipikae.assessmentservice.risk.validator.ModelPatientValidator;

/**
 * Validator with model object.
 * @author tipikae
 * @version 1.0
 *
 */
public class ModelValidator extends AbstractValidator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ModelValidator.class);
	
	private IModelValidator modelValidator;
	private Object object;

	public ModelValidator(String expression, Object object) {
		super(expression);
		this.object = object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean valid() throws ExpressionValidationException, ValidatorNotFoundException {
		LOGGER.debug("valid: model expression=" + expression);
		
		if(object instanceof Patient) {
			Patient patient = (Patient) object;
			modelValidator = new ModelPatientValidator(patient);
			
			return modelValidator.valid(expression);
		}
		
		LOGGER.debug("valid: validator model for " + object.getClass().getSimpleName()
				+ " object not found");
		throw new ValidatorNotFoundException("validator model for " + object.getClass().getSimpleName() 
				+ " object not found");
	}

}
