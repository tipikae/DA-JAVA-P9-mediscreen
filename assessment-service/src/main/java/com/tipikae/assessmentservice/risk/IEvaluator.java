/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import com.tipikae.assessmentservice.exception.BadOperationException2;
import com.tipikae.assessmentservice.exception.ClientException;
import com.tipikae.assessmentservice.exception.FieldNotFoundException2;
import com.tipikae.assessmentservice.exception.OperatorNotFoundException2;
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
	 * @throws OperatorNotFoundException2 
	 * @throws FieldNotFoundException2 
	 * @throws BadOperationException2 
	 * @throws ClientException 
	 */
	boolean evaluate(String operation) 
			throws OperatorNotFoundException2, FieldNotFoundException2, 
			BadOperationException2, ClientException;
	
	/**
	 * Set patient.
	 * @param patient
	 */
	void setPatient(Patient patient);
}
