/**
 * 
 */
package com.tipikae.assessmentservice.risk.validator;

import com.tipikae.assessmentservice.exception.ExpressionValidationException;

/**
 * Model validator interface.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IModelValidator {

	/**
	 * Check if an expression is valid.
	 * @param expression String
	 * @return boolean
	 * @throws ExpressionValidationException 
	 */
	boolean valid(String expression) 
			throws ExpressionValidationException;
}
