/**
 * 
 */
package com.tipikae.assessmentservice.assessment;

import java.util.List;

import com.tipikae.assessmentservice.model.Assessment;
import com.tipikae.assessmentservice.model.Note;
import com.tipikae.assessmentservice.model.Patient;

/**
 * Process data for assessment.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IProcessData {

	/**
	 * Calculate health risk.
	 * @param patient Patient
	 * @param notes List
	 * @return Assessment
	 */
	Assessment calculate(Patient patient, List<Note> notes);
}
