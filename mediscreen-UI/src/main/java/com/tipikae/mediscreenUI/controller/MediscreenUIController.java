/**
 * 
 */
package com.tipikae.mediscreenUI.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tipikae.mediscreenUI.client.IPatientServiceClient;
import com.tipikae.mediscreenUI.dto.NewPatientDTO;
import com.tipikae.mediscreenUI.dto.UpdatePatientDTO;
import com.tipikae.mediscreenUI.model.Patient;

/**
 * Controller for Mediscreeen-UI.
 * @author tipikae
 * @version 1.0
 *
 */
@Controller
@RequestMapping("/patient")
public class MediscreenUIController {

	private static final Logger LOGGER = LoggerFactory.getLogger(MediscreenUIController.class);
	
	@Autowired
	private IPatientServiceClient patientClient;
	
	/**
	 * Get all patients list.
	 * @param model Model
	 * @return String
	 */
	@GetMapping("/list")
	public String getAllPatients(Model model) {
		LOGGER.info("Getting all patients");
		List<Patient> patients = patientClient.getPatients();
		model.addAttribute("patients", patients);
		return "patient/list";
	}
	
	/**
	 * Get a patient.
	 * @param id Integer
	 * @param model Model
	 * @return String
	 */
	@GetMapping("/{id}")
	public String getPatient(@PathVariable("id") @Positive Integer id, Model model) {
		LOGGER.info("Getting patient with id=" + id);
		Patient patient = patientClient.getPatient(id);
		model.addAttribute("patient", patient);
		return "patient/get";
	}
	
	/**
	 * Get add form.
	 * @param model Model
	 * @return String
	 */
	@GetMapping("/add")
	public String showAddForm(Model model) {
		return "patient/add";
	}

	/**
	 * Add patient.
	 * @param newPatientDTO NewPatientDTO
	 * @param result BindingResult
	 * @param model Model
	 * @return String
	 */
	@PostMapping("/add")
	public String addPatient(
			@ModelAttribute("patient") @Valid NewPatientDTO newPatientDTO,
			BindingResult result, 
    		Model model) {
		return null;
	}
	
	/**
	 * Get update form.
	 * @param id Integer
	 * @param model Model
	 * @return String
	 */
	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") @Positive Integer id, Model model) {
		return "patient/update";
	}
	
	/**
	 * Update a	patient.
	 * @param id Integer
	 * @param updatePatientDTO UpdatePatientDTO
	 * @param result BindingResult
	 * @param model Model
	 * @return String
	 */
	@PutMapping("/update/{id}")
	public String updatePatient(
    		@PathVariable("id") @Positive Integer id, 
    		@ModelAttribute("bidList") @Valid UpdatePatientDTO updatePatientDTO,
            BindingResult result, 
    		Model model) {
		return null;
	}
}
