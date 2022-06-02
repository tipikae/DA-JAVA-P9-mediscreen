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

import com.tipikae.mediscreenUI.dto.AssessmentByFamilyDTO;
import com.tipikae.mediscreenUI.dto.AssessmentByIdDTO;
import com.tipikae.mediscreenUI.exception.NotFoundException;
import com.tipikae.mediscreenUI.model.Assessment;
import com.tipikae.mediscreenUI.service.IAssessmentService;

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
	private IAssessmentService assessmentService;
	
	/**
	 * Get assessment by id.
	 * @param id long
	 * @param model Model
	 * @return String
	 * @throws NotFoundException 
	 */
	@GetMapping("/id/{id}")
	public String getAssessmentById(
			@PathVariable("id") @NotNull @Positive long id,
			Model model) {
		LOGGER.info("getAssessmentById: id=" + id);
		
		try {
			model.addAttribute("assessment", 
					assessmentService.getAssessmentById(new AssessmentByIdDTO(id)));
			return "patient/get :: #assessmentById";
		} catch (NotFoundException e) {
			LOGGER.debug("getAssessmentById: patient with id=" + id + " not found.");
			model.addAttribute("assessment", new Assessment("Error: patient not found."));
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
		List<Assessment> assessments = assessmentService.getAssessmentsByFamily(new AssessmentByFamilyDTO(family));
		if(assessments.isEmpty()) {
			model.addAttribute("assessments", List.of(new Assessment("No family members.")));
			return "patient/get :: #assessmentByFamily";
		}
		
		model.addAttribute("assessments", assessments);
		return "patient/get :: #assessmentByFamily";
	}
	
}
