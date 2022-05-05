/**
 * 
 */
package com.tipikae.assessmentservice.risk.validator;

import com.tipikae.assessmentservice.exception.ExpressionValidationException;

/**
 * Model validator of an expression.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IModelValidator {
	
	/**
	 * Set object.
	 * @param obj
	 */
	void setObject(Object obj);

	/**
	 * Check if an expression is valid.
	 * @param expression String
	 * @return boolean
	 * @throws ExpressionValidationException 
	 */
	boolean valid(String expression) throws ExpressionValidationException;
}
