package com.tipikae.assessmentservice.risk;

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
	 */
	boolean evaluateExpression(String expression);
}
