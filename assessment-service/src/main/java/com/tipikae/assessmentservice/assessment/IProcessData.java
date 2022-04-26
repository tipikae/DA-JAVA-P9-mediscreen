/**
 * 
 */
package com.tipikae.assessmentservice.assessment;

import java.util.List;

import com.tipikae.assessmentservice.model.Note;

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
	 * @param sex char
	 * @param notes List
	 * @return String
	 */
	String getRisk(int age, char sex, List<Note> notes);
}
