/**
 * 
 */
package com.tipikae.assessmentservice.risk.validator;

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
	 * @return boolea
	 */
	boolean valid(String operand, String value);
}
