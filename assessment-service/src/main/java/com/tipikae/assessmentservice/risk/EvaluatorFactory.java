/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.tipikae.assessmentservice.model.Patient;

/**
 * Factory of Evaluator objects.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class EvaluatorFactory {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EvaluatorFactory.class);
	private static final String START_MODEL = "(^[A-Z]{1}\\.).*";

	@Autowired
	@Qualifier("patientEvaluator")
	private IEvaluator patientEvaluator;
	
	@Autowired
	@Qualifier("triggerEvaluator")
	private IEvaluator triggerEvaluator;
	
	private Patient patient;
	
	/**
	 * Set the patient.
	 * @param patient
	 */
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	
	/**
	 * Get an evaluator.
	 * @param operation
	 * @return IEvaluator
	 */
	public IEvaluator getEvaluator(String operation) {
		LOGGER.debug("getEvaluator: operation=" + operation);
		if(operation.matches(START_MODEL)) {
			patientEvaluator.setPatient(patient);
			return patientEvaluator;
		}
		
		triggerEvaluator.setPatient(patient);
		return triggerEvaluator;
	}
}
