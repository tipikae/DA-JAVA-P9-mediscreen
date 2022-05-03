/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import com.tipikae.assessmentservice.exception.RiskNotFoundException;
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
	 * @param count int
	 * @param age int
	 * @param sex char
	 * @return Risk
	 * @throws RiskNotFoundException
	 */
	Risk calculateRisk(int count, int age, char sex) throws RiskNotFoundException;
}
