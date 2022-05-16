/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Operation parser.
 * @author tipikae
 * @version 1.0
 *
 */
public class OperationParser {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OperationParser.class);
	private static final String MODEL_ELEMENTS = "%s\\.([a-zA-Z0-9]+)\\s?([=<>!]{1,2})\\s?([a-zA-Z0-9]+)";
	private static final String METHOD_ELEMENTS = "([a-zA-Z0-9]+)\\s?([=<>!]{1,2})\\s?([a-zA-Z0-9]+)";

	/**
	 * Get elements of an operation with model.
	 * @param model char
	 * @param operation String
	 * @return List
	 */
	public List<String> getModelElements(char model, String operation) {
		LOGGER.debug("getModelElements: model=" + model + ", expression=" + operation);
		String pattern = String.format(MODEL_ELEMENTS, model);
		Matcher matcher = Pattern.compile(pattern).matcher(operation);
		
		return getElements(matcher);
	}

	/**
	 * Get elements of an operation with method.
	 * @param operation String
	 * @return List
	 */
	public List<String> getMethodElements(String operation) {
		LOGGER.debug("getMethodElements: expression=" + operation);
		Matcher matcher = Pattern.compile(METHOD_ELEMENTS).matcher(operation);
		
		return getElements(matcher);
	}
	
	// return a list of found elements or an empty list.
	private List<String> getElements(Matcher matcher) {
		if(matcher.find()) {
    		int count = matcher.groupCount();
		    
    		return IntStream.rangeClosed(1, count)
		        .mapToObj(i -> matcher.group(i))
		        .collect(Collectors.toList());
		}
		
		return List.of();
	}

}
