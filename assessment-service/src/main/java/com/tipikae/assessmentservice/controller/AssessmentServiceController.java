/**
 * 
 */
package com.tipikae.assessmentservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tipikae.assessmentservice.dto.AssessmentByFamilyDTO;
import com.tipikae.assessmentservice.dto.AssessmentByIdDTO;
import com.tipikae.assessmentservice.dto.AssessmentDTO;
import com.tipikae.assessmentservice.exception.BadRequestException;
import com.tipikae.assessmentservice.exception.HttpClientException;
import com.tipikae.assessmentservice.exception.NotFoundException;
import com.tipikae.assessmentservice.service.IAssessmentServiceService;

/**
 * Assessment service controller.
 * @author tipikae
 * @version 1.0
 *
 */
@RestController
@Validated
@RequestMapping("/assess")
public class AssessmentServiceController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AssessmentServiceController.class);
	
	@Autowired
	private IAssessmentServiceService assessmentService;
	
	/**
	 * Assess by patient id.
	 * @param assessmentByIdDTO AssessmentByIdDTO
	 * @return ResponseEntity
	 * @throws NotFoundException
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	@PostMapping(value = "/id", consumes = {"application/json"})
	public ResponseEntity<AssessmentDTO> assessById(
			@RequestBody @Valid AssessmentByIdDTO assessmentByIdDTO) 
					throws NotFoundException, BadRequestException, HttpClientException {
		LOGGER.info("assessById: patId=" + assessmentByIdDTO.getPatId());
		AssessmentDTO assessmentDTO = assessmentService.assessDiabetesById(assessmentByIdDTO);
		
		return new ResponseEntity<AssessmentDTO>(assessmentDTO, HttpStatus.OK);
	}
	
	/**
	 * Assess by family name.
	 * @param assessmentByFamilyDTO AssessmentByFamilyDTO
	 * @return ResponseEntity
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	@PostMapping(value = "/familyName", consumes = {"application/json"})
	public ResponseEntity<List<AssessmentDTO>> assessByFamilyName(
			@RequestBody @Valid AssessmentByFamilyDTO assessmentByFamilyDTO) 
					throws BadRequestException, HttpClientException {
		LOGGER.info("assessByFamilyName: family=" + assessmentByFamilyDTO.getFamilyName());
		List<AssessmentDTO> assessmentDTOs = assessmentService.assessDiabetesByFamilyName(assessmentByFamilyDTO);
		
		return new ResponseEntity<List<AssessmentDTO>>(assessmentDTOs, HttpStatus.OK);
	}

}
