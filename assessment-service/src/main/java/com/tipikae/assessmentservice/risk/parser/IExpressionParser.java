/**
 * 
 */
package com.tipikae.assessmentservice.risk.parser;

import java.util.List;

/**
 * Expression parser.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IExpressionParser {

	/**
	 * Get elements of an expression with model.
	 * @param model char
	 * @param expression String
	 * @return List
	 */
	List<String> getModelElements(char model, String expression);

	/**
	 * Get elements of an expression with method.
	 * @param expression String
	 * @return List
	 */
	List<String> getMethodElements(String expression);
}
