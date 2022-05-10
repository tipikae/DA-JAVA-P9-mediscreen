/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Formula parser.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class FormulaParser {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FormulaParser.class);
	private static final String OPERATIONS = "\\[(.*?)\\]";
	private static final String OPERATORS = "\\]\\s?(.*?)\\s?\\[";
	

	/**
	 * Get operations from formula.
	 * @param formula String
	 * @return List
	 */
	public List<String> getOperations(String formula) {
		LOGGER.debug("getOperations: from formula=" + formula);
		List<String> expressions = new ArrayList<>();
		Matcher matcher = Pattern.compile(OPERATIONS).matcher(formula);
		while(matcher.find()) {
			expressions.add(matcher.group(1));
		}
		
		return expressions;
	}

	/**
	 * Get operators from formula.
	 * @param formula String
	 * @return List
	 */
	public List<String> getOperators(String formula) {
		LOGGER.debug("getOperators: from formula=" + formula);
		List<String> operandes = new ArrayList<>();
		Matcher matcher = Pattern.compile(OPERATORS).matcher(formula);
		while(matcher.find()) {
			operandes.add(matcher.group(1));
		}
		
		return operandes;
	}

}
