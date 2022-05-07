/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import com.tipikae.assessmentservice.model.Patient;

/**
 * View of the assessment result.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IViewResult {

	/**
	 * Get the result view.
	 * @param patient Patient
	 * @param age int
	 * @param result String
	 * @return String
	 */
	String getResultView(Patient patient, int age, String result);
	
	/**
	 * Get the error view.
	 * @param patient Patient
	 * @param error String
	 * @return String
	 */
	String getErrorView(Patient patient, String error);
}
