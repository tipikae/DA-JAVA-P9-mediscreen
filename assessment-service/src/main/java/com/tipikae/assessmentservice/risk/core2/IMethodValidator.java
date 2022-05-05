/**
 * 
 */
package com.tipikae.assessmentservice.risk.core2;

/**
 * Method validate of an expression.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IMethodValidator {

	/**
	 * Check if an expression is valid.
	 * @param method String
	 * @param operand String
	 * @param expected String
	 * @return boolean
	 */
	boolean valid(String method, String operand, String expected);
}
