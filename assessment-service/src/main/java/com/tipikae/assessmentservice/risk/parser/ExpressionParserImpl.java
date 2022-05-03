/**
 * 
 */
package com.tipikae.assessmentservice.risk.parser;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Expression parser.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class ExpressionParserImpl implements IExpressionParser {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExpressionParserImpl.class);
	private static final String MODEL_ELEMENTS = "%s\\.([a-zA-Z0-9]+)\\s?([=<>!]{1,2})\\s?([a-zA-Z0-9]+)";
	private static final String METHOD_ELEMENTS = "([a-zA-Z0-9]+)\\s?([=<>!]{1,2})\\s?([a-zA-Z0-9]+)";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getModelElements(char model, String expression) {
		LOGGER.debug("getModelElements: model=" + model + ", expression=" + expression);
		String pattern = String.format(MODEL_ELEMENTS, model);
		Matcher matcher = Pattern.compile(pattern).matcher(expression);
		
		return getElements(matcher);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getMethodElements(String expression) {
		LOGGER.debug("getMethodElements: expression=" + expression);
		Matcher matcher = Pattern.compile(METHOD_ELEMENTS).matcher(expression);
		
		return getElements(matcher);
	}
	
	// return a list of found elements or an empty list.
	private List<String> getElements(Matcher matcher) {
		LOGGER.debug("getElements");
		if(matcher.find()) {
    		int count = matcher.groupCount();
		    
    		return IntStream.rangeClosed(1, count)
		        .mapToObj(i -> matcher.group(i))
		        .collect(Collectors.toList());
		}
		
		return List.of();
	}

}
