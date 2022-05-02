/**
 * 
 */
package com.tipikae.assessmentservice.service;

import java.util.List;

import com.tipikae.assessmentservice.assessment.Risk;
import com.tipikae.assessmentservice.model.Note;

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
	 * @param sex char
	 * @param notes List
	 * @return String
	 * @throws RiskNotFoundException
	 */
	Risk getRisk(int age, char sex, List<Note> notes) throws RiskNotFoundException;
}
