/**
 * 
 */
package com.tipikae.mediscreenUI.service;

import java.util.List;

import com.tipikae.mediscreenUI.dto.AssessmentByFamilyDTO;
import com.tipikae.mediscreenUI.dto.AssessmentByIdDTO;
import com.tipikae.mediscreenUI.exception.NotFoundException;
import com.tipikae.mediscreenUI.model.Assessment;

/**
 * Assessment service.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IAssessmentService {

	/**
	 * Get an assessment by patientId.
	 * @param assessmentByIdDTO AssessmentByIdDTO
	 * @return Assessment
	 * @throws NotFoundException
	 */
	Assessment getAssessmentById(AssessmentByIdDTO assessmentByIdDTO) throws NotFoundException;

	/**
	 * Get an assessments list by family name.
	 * @param assessmentByFamilyDTO AssessmentByFamilyDTO
	 * @return List
	 */
	List<Assessment> getAssessmentsByFamily(AssessmentByFamilyDTO assessmentByFamilyDTO);
}
