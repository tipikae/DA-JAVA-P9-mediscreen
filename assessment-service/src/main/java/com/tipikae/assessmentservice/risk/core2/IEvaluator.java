/**
 * 
 */
package com.tipikae.assessmentservice.risk.core2;

/**
 * Expression evaluator.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IEvaluator {

	/**
	 * Evaluate an expression.
	 * @param obj Object
	 * @param expression String
	 * @return boolean
	 */
	boolean evaluateExpression(Object obj, String expression);
}
