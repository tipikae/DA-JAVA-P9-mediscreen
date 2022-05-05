/**
 * 
 */
package com.tipikae.assessmentservice.risk.core2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Method evaluator of an expression.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class MethodEvaluatorImpl implements IMethodEvaluator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodEvaluatorImpl.class);
	
	@Autowired
	private IMethodEvaluator methodTriggerEvaluator;

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
