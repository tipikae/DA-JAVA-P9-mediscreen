/**
 * 
 */
package com.tipikae.assessmentservice.risk.validator;

import com.tipikae.assessmentservice.exception.ExpressionValidationException;

/**
 * Method validate of an expression.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IMethodValidator {
	
	/**
	 * Set object.
	 * @param obj
	 */
	void setObject(Object obj);

	/**
	 * Check if an expression is valid.
	 * @param operand String
	 * @param expected String
	 * @return boolean
	 * @throws ExpressionValidationException 
	 */
	boolean valid(String operand, String expected) throws ExpressionValidationException;
}
