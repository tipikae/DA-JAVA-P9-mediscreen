/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tipikae.assessmentservice.model.Patient;

/**
 * View of the assessment result.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class ViewResultImpl implements IViewResult {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ViewResultImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getResultView(Patient patient, int age, String result) {
		LOGGER.debug("getResultView: patientId=" + patient.getId() + ", result=" + result);
		return "Patient: " + patient.getGiven() + " " + patient.getFamily() 
			+ " (age " + age + ") diabetes assessment is: " + result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getErrorView(Patient patient, String error) {
		LOGGER.debug("getErrorView: patientId=" + patient.getId() + ", error=" + error);
		return patient.getGiven() + ": service unavailable.";
	}

}
