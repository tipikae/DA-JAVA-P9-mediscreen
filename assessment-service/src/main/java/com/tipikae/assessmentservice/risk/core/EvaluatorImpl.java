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
import com.tipikae.assessmentservice.util.IUtil;

/**
 * Expression evaluator.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class EvaluatorImpl implements IEvaluator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EvaluatorImpl.class);
	private static final String START_MODEL = "(^[A-Z]{1,2}\\.).*";
	
	@Autowired
	private IMethodEvaluator methodEvaluator;
	
	@Autowired
	private IModelEvaluator modelEvaluator;
	
	@Autowired
	private IUtil util;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean evaluateExpression(Object obj, String expression) 
			throws ValidatorNotFoundException, ExpressionValidationException {
		LOGGER.debug("evaluateExpression: expression=" + expression);
		
		if(util.matches(expression, START_MODEL)) {
			return modelEvaluator.evaluateExpression(obj, expression);
		}
		
		return methodEvaluator.evaluateExpression(obj, expression);
	}

}
