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
	private static final String ELEMENTS = "([a-zA-Z0-9]+)\\s?([=<>!]{1,2})\\s?([a-zA-Z0-9]+)";

	/**
	 * Get elements of an operation.
	 * @param operation String
	 * @return List
	 */
	public List<String> getElements(String operation) {
		LOGGER.debug("getElements: operation=" + operation);
		Matcher matcher = Pattern.compile(ELEMENTS).matcher(operation);
		
		if(matcher.find()) {
    		int count = matcher.groupCount();
		    
    		return IntStream.rangeClosed(1, count)
		        .mapToObj(i -> matcher.group(i))
		        .collect(Collectors.toList());
		}
		
		return List.of();
	}

}
