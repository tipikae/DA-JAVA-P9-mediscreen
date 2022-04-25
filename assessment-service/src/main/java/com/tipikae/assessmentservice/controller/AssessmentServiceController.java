/**
 * 
 */
package com.tipikae.assessmentservice.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tipikae.assessmentservice.dto.AssessmentByFamilyDTO;
import com.tipikae.assessmentservice.dto.AssessmentByIdDTO;
import com.tipikae.assessmentservice.dto.AssessmentDTO;
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
	
	@PostMapping(value = "/id", consumes = {"application/json"})
	public ResponseEntity<AssessmentDTO> assessById(@RequestBody @Valid AssessmentByIdDTO dto) {
		return null;
	}
	
	@PostMapping(value = "/familyName", consumes = {"application/json"})
	public ResponseEntity<AssessmentDTO> assessByFamilyName(@RequestBody @Valid AssessmentByFamilyDTO dto) {
		return null;
	}

}
