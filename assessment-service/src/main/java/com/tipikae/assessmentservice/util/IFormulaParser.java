/**
 * 
 */
package com.tipikae.assessmentservice.util;

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
	 * Get operandes from string.
	 * @param formula String
	 * @return List
	 */
	List<String> getOperandes(String formula);
}
