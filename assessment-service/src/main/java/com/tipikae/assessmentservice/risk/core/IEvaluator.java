/**
 * 
 */
package com.tipikae.assessmentservice.risk.core;

import com.tipikae.assessmentservice.exception.ExpressionValidationException;
import com.tipikae.assessmentservice.exception.ValidatorNotFoundException;

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
	 * @throws ValidatorNotFoundException 
	 * @throws ExpressionValidationException 
	 */
	boolean evaluateExpression(Object obj, String expression) 
			throws ValidatorNotFoundException, ExpressionValidationException;
}
