/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Factory of Evaluator objects.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class EvaluatorFactory {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EvaluatorFactory.class);
	private static final String START_MODEL = "(^[A-Z]{1,2}\\.).*";

	@Autowired
	private IEvaluator patientEvaluator;
	
	@Autowired
	private IEvaluator triggerEvaluator;
	
	public IEvaluator getEvaluator(String operation) {
		LOGGER.debug("getEvaluator: operation=" + operation);
		if(operation.matches(START_MODEL)) {
			return patientEvaluator;
		}
		return triggerEvaluator;
	}
}
