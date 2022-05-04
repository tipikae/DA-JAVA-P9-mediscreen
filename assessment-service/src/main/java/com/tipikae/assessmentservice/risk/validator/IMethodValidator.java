/**
 * 
 */
package com.tipikae.assessmentservice.risk.validator;

import com.tipikae.assessmentservice.exception.ExpressionValidationException;

/**
 * Method validator.
 * @author tipikae
 * @version1.0
 *
 */
public interface IMethodValidator {

	/**
	 * Check if the expression is valid or not.
	 * @param operand String
	 * @param value String
	 * @return boolean
	 * @throws ExpressionValidationException 
	 */
	boolean valid(String operand, String value) throws ExpressionValidationException;
}
