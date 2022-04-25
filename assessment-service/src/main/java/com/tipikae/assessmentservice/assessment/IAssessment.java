/**
 * 
 */
package com.tipikae.assessmentservice.assessment;

import java.util.List;

import com.tipikae.assessmentservice.model.Note;

/**
 * Assessment of health risks.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IAssessment {

	/**
	 * Assess diabetes risk of a patient.
	 * @param age int
	 * @param sex char
	 * @param notes List
	 * @return String
	 */
	String assessDiabetes(int age, char sex, List<Note> notes);
}
