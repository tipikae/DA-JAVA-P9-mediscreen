/**
 * 
 */
package com.tipikae.assessmentservice.risk.parser;

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
public class FormulaParserImpl implements IFormulaParser {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FormulaParserImpl.class);
	private static final String EXPRESSION = "\\[(.*?)\\]";
	private static final String OPERANDE = "\\]\\s?(.*?)\\s?\\[";
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getExpressions(String formula) {
		LOGGER.debug("getExpressions: from formula=" + formula);
		List<String> expressions = new ArrayList<>();
		Matcher matcher = Pattern.compile(EXPRESSION).matcher(formula);
		while(matcher.find()) {
			expressions.add(matcher.group(1));
		}
		
		return expressions;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getOperands(String formula) {
		LOGGER.debug("getOperandes: from formula=" + formula);
		List<String> operandes = new ArrayList<>();
		Matcher matcher = Pattern.compile(OPERANDE).matcher(formula);
		while(matcher.find()) {
			operandes.add(matcher.group(1));
		}
		
		return operandes;
	}

}
