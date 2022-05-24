/**
 * 
 */
package com.tipikae.mediscreenUI.client;

import java.util.List;

import com.tipikae.mediscreenUI.dto.AssessmentByFamilyDTO;
import com.tipikae.mediscreenUI.dto.AssessmentByIdDTO;
import com.tipikae.mediscreenUI.exception.NotFoundException;
import com.tipikae.mediscreenUI.model.Assessment;

import feign.Headers;
import feign.RequestLine;

/**
 * Assessment service Feign client.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IAssessmentServiceClient {

	/**
	 * Get an assessment by patientId.
	 * @param assessmentByIdDTO AssessmentByIdDTO
	 * @return Assessment
	 * @throws NotFoundException
	 */
	@RequestLine("GET /assess/id")
    @Headers("Content-Type: application/json")
	Assessment getAssessmentById(AssessmentByIdDTO assessmentByIdDTO) throws NotFoundException;

	/**
	 * Get an assessments list by family name.
	 * @param assessmentByFamilyDTO AssessmentByFamilyDTO
	 * @return List
	 */
	@RequestLine("GET /assess/familyName")
    @Headers("Content-Type: application/json")
	List<Assessment> getAssessmentsByFamily(AssessmentByFamilyDTO assessmentByFamilyDTO);
}
