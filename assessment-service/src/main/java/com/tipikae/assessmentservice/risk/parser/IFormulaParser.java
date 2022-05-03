/**
 * 
 */
package com.tipikae.assessmentservice.risk.parser;

import java.util.List;

/**
 * String parser.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IFormulaParser {

	/**
	 * Get expressions from string.
	 * @param formula String
	 * @return List
	 */
	List<String> getExpressions(String formula);
	
	/**
	 * Get operands from string.
	 * @param formula String
	 * @return List
	 */
	List<String> getOperands(String formula);
}
