/**
 * 
 */
package com.tipikae.assessmentservice.risk.core2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
	public boolean evaluateExpression(Object obj, String expression) {
		LOGGER.debug("evaluateExpression: expression=" + expression);
		// TODO Auto-generated method stub
		return false;
	}

}
