/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import com.tipikae.assessmentservice.exception.BadOperationException;
import com.tipikae.assessmentservice.exception.ClientException;
import com.tipikae.assessmentservice.exception.NotFoundException;
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
	 * @param operation String
	 * @return boolean
	 * @throws NotFoundException 
	 * @throws BadOperationException 
	 * @throws ClientException 
	 */
	boolean evaluate(String operation) throws NotFoundException, 
			BadOperationException, ClientException;
	
	/**
	 * Set patient.
	 * @param patient
	 */
	void setPatient(Patient patient);
}
