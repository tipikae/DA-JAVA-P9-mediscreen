/**
 * 
 */
package com.tipikae.assessmentservice.risk2;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Factory of Evaluator objects.
 * @author tipikae
 * @version 1.0
 *
 */
public class EvaluatorFactory {

	@Autowired
	private IEvaluator patientEvaluator;
	
	@Autowired
	private IEvaluator triggerEvaluator;
	
	
}
