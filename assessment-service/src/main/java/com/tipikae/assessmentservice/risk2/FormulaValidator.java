/**
 * 
 */
package com.tipikae.assessmentservice.risk2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Validate a formula.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class FormulaValidator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FormulaValidator.class);
	private static final String VALIDATOR = "^\\[[^\\[^\\]]+\\](?:\\s?(?:AND|OR)\\s?\\[[^\\[^\\]]+\\])*$";
	
	/**
	 * Validate the syntax of a formula.
	 * @param formula String
	 * @return boolean
	 */
	public boolean validate(String formula) {
		if(formula.matches(VALIDATOR)) {
			LOGGER.debug("validate: formula=" + formula + " is valid.");
			return true;
		}
		
		LOGGER.debug("validate: formula=" + formula + " is not valid.");
		return false;
	}

}
