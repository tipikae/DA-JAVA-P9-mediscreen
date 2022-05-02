/**
 * 
 */
package com.tipikae.assessmentservice.service;

import java.util.List;

import com.tipikae.assessmentservice.assessment.Risk;
import com.tipikae.assessmentservice.model.Note;
import com.tipikae.assessmentservice.validation.Gender;

/**
 * Formula service.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IRiskService {

	/**
	 * Get a patient risk.
	 * @param age int
	 * @param gender Gender
	 * @param notes List
	 * @return String
	 * @throws RiskNotFoundException
	 */
	Risk getRisk(int age, Gender gender, List<Note> notes) throws RiskNotFoundException;
}
