/**
 * 
 */
package com.tipikae.mediscreenUI.controller;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tipikae.mediscreenUI.client.IAssessmentServiceClient;
import com.tipikae.mediscreenUI.dto.AssessmentByFamilyDTO;
import com.tipikae.mediscreenUI.dto.AssessmentByIdDTO;
import com.tipikae.mediscreenUI.exception.PatientNotFoundException;
import com.tipikae.mediscreenUI.model.Assessment;

/**
 * Assessment controller.
 * @author tipikae
 * @version 1.0
 *
 */
@Controller
@RequestMapping("/assess")
public class AssessmentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AssessmentController.class);
	
	@Autowired
	private IAssessmentServiceClient assessmentClient;
	
	/**
	 * Get assessment by id.
	 * @param id long
	 * @param model Model
	 * @return String
	 * @throws PatientNotFoundException 
	 */
	@GetMapping("/id/{id}")
	public String getAssessmentById(
			@PathVariable("id") @NotNull @Positive long id,
			Model model) {
		LOGGER.info("getAssessmentById: id=" + id);
		
		try {
			model.addAttribute("assessmentById", 
					assessmentClient.getAssessmentById(new AssessmentByIdDTO(id)));
			return "patient/get :: #assessmentById";
		} catch (PatientNotFoundException e) {
			LOGGER.debug("getAssessmentById: patient with id=" + id + " not found.");
			model.addAttribute("assessmentById", "Error: patient not found.");
			return "patient/get :: #assessmentById";
		}
	}
	
	/**
	 * Get assessment by family name.
	 * @param family String
	 * @param model Model
	 * @return String
	 */
	@GetMapping("/familyName/{familyName}")
	public String getAssessmentByFamily(
			@PathVariable("familyName") @NotBlank String family,
			Model model) {
		LOGGER.info("getAssessmentByFamily: family=" + family);
		List<Assessment> assessments = assessmentClient.getAssessmentsByFamily(new AssessmentByFamilyDTO(family));
		if(assessments.isEmpty()) {
			model.addAttribute("assessmentByFamily", "No family members.");
			return "patient/get :: #assessmentByFamily";
		}
		
		model.addAttribute("assessmentByFamily", assessments);
		return "patient/get :: #assessmentByFamily";
	}
	
}
