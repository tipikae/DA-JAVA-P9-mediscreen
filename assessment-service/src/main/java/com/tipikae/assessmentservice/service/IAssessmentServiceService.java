/**
 * 
 */
package com.tipikae.assessmentservice.service;

import java.util.List;

import com.tipikae.assessmentservice.dto.AssessmentByFamilyDTO;
import com.tipikae.assessmentservice.dto.AssessmentByIdDTO;
import com.tipikae.assessmentservice.dto.AssessmentDTO;
import com.tipikae.assessmentservice.exception.BadRequestException;
import com.tipikae.assessmentservice.exception.HttpClientException;
import com.tipikae.assessmentservice.exception.NotFoundException;

/**
 * Assessment service service.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IAssessmentServiceService {

	/**
	 * Assess risk of diabetes of a patient.
	 * @param dto AssessmentByIdDTO
	 * @return AssessmentDTO
	 * @throws NotFoundException
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	AssessmentDTO assessDiabetesById(AssessmentByIdDTO dto) 
			throws NotFoundException, BadRequestException, HttpClientException;
	
	/**
	 * Assess risk of diabetes of a family.
	 * @param dto AssessmentByFamilyDTO
	 * @return List
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	List<AssessmentDTO> assessDiabetesByFamilyName(AssessmentByFamilyDTO dto) 
			throws BadRequestException, HttpClientException;
}
