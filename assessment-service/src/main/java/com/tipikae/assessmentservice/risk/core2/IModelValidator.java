/**
 * 
 */
package com.tipikae.assessmentservice.risk.core2;

/**
 * Model validator of an expression.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IModelValidator {

	/**
	 * Check if an expression is valid.
	 * @param field String
	 * @param operand String
	 * @param expected String
	 * @return boolean
	 */
	boolean valid(String field, String operand, String expected);
}
