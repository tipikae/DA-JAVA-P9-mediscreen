/**
 * 
 */
package com.tipikae.assessmentservice.assessment;

import java.util.List;

import com.tipikae.assessmentservice.model.Note;
import com.tipikae.assessmentservice.validation.Gender;

/**
 * Process data for assessment.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IProcessData {

	/**
	 * Get health risk.
	 * @param age int
	 * @param gender Gender
	 * @param notes List
	 * @return String
	 * @throws Exception
	 */
	String getRisk(int age, Gender gender, List<Note> notes) throws Exception;
}