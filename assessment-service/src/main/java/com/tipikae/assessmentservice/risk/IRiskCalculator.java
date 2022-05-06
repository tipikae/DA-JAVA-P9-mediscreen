/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import com.tipikae.assessmentservice.exception.RiskNotFoundException;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.model.Risk;

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
	 * @return Risk
	 * @throws RiskNotFoundException
	 */
	Risk calculateRisk(Patient patient) throws RiskNotFoundException;
}
