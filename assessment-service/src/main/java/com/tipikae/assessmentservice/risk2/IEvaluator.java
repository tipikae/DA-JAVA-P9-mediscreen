/**
 * 
 */
package com.tipikae.assessmentservice.risk2;

import com.tipikae.assessmentservice.model.Patient;

/**
 * Evaluator.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IEvaluator {
	
	/**
	 * Evaluate an operation.
	 * @param patient Patient
	 * @param operation String
	 * @return boolean
	 */
	boolean evaluate(Patient patient, String operation);
}
