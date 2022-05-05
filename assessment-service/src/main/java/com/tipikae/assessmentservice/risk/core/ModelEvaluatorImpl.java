/**
 * 
 */
package com.tipikae.assessmentservice.risk.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tipikae.assessmentservice.exception.ExpressionValidationException;
import com.tipikae.assessmentservice.exception.ValidatorNotFoundException;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.risk.validator.IModelValidator;

/**
 * Model evaluator for an expression.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class ModelEvaluatorImpl implements IModelEvaluator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ModelEvaluatorImpl.class);
	
	@Autowired
	private IModelValidator modelPatientValidator;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean evaluateExpression(Object obj, String expression) 
			throws ValidatorNotFoundException, ExpressionValidationException {
		LOGGER.debug("evaluateExpression: expression=" + expression);
		
		if(obj instanceof Patient) {
			LOGGER.debug("evaluateExpression: obj=Patient");
			modelPatientValidator.setObject(obj);
			return modelPatientValidator.valid(expression);
		}
		
		LOGGER.debug("evaluateExpression: validator not found for obj=" + obj.getClass().getSimpleName());
		throw new ValidatorNotFoundException("Validator not found for obj=" + obj.getClass().getSimpleName());
	}

}
