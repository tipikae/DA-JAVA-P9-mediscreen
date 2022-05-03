package com.tipikae.assessmentservice.risk;

import com.tipikae.assessmentservice.exception.ExpressionValidationException;

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
	 */
	boolean evaluateExpression(String expression) throws ExpressionValidationException;
}
