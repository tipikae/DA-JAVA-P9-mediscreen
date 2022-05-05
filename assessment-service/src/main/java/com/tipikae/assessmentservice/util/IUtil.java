/**
 * 
 */
package com.tipikae.assessmentservice.util;

import java.time.LocalDate;

/**
 * Utilities.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IUtil {

	/**
	 * Calculate age from date.
	 * @param dob LocalDate
	 * @return int
	 */
	int calculateAge(LocalDate dob);
	
	/**
	 * Check if an expression matches with a regex.
	 * @param expression String
	 * @param regex String
	 * @return boolean
	 */
	boolean matches(String expression, String regex);
}
