package com.tipikae.assessmentservice.risk.core;

import com.tipikae.assessmentservice.exception.ExpressionValidationException;
import com.tipikae.assessmentservice.exception.ValidatorNotFoundException;

/**
 * Evaluator.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IEvaluator {

	/**
	 * Evaluate an expression.
	 * @param expression String
	 * @return boolean
	 * @throws ExpressionValidationException 
	 * @throws ValidatorNotFoundException 
	 */
	boolean evaluateExpression(String expression) 
			throws ExpressionValidationException, ValidatorNotFoundException;
}
