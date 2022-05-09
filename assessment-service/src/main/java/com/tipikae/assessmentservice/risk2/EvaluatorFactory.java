/**
 * 
 */
package com.tipikae.assessmentservice.risk2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Factory of Evaluator objects.
 * @author tipikae
 * @version 1.0
 *
 */
public class EvaluatorFactory {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EvaluatorFactory.class);

	@Autowired
	private IEvaluator patientEvaluator;
	
	@Autowired
	private IEvaluator triggerEvaluator;
	
	public IEvaluator getEvaluator(String operation) {
		LOGGER.debug("getEvaluator: operation=" + operation);
		
		return null;
	}
}
