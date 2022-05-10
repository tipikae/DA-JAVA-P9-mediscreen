/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import com.tipikae.assessmentservice.exception.RiskNotFoundException;
import com.tipikae.assessmentservice.model.Patient;

/**
 * Risk calculator.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IRiskCalculator {

	/**
	 * Calculate risk.
	 * @param patient Patient
	 * @return String
	 * @throws RiskNotFoundException
	 */
	String calculateRisk(Patient patient) throws RiskNotFoundException;
}
