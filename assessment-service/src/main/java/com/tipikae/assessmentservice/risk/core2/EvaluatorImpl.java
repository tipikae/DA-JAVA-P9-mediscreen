/**
 * 
 */
package com.tipikae.assessmentservice.risk.core2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Expression evaluator.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class EvaluatorImpl implements IEvaluator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EvaluatorImpl.class);
	private static final String START_MODEL = "^[A-Z]{1,2}\\.";
	
	@Autowired
	private IMethodEvaluator methodEvaluator;
	
	@Autowired
	private IModelEvaluator modelEvaluator;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean evaluateExpression(Object obj, String expression) {
		LOGGER.debug("evaluateExpression: expression=" + expression);
		
		if(expression.matches(START_MODEL)) {
			return modelEvaluator.evaluateExpression(obj, expression);
		}
		return methodEvaluator.evaluateExpression(obj, expression);
	}

}
