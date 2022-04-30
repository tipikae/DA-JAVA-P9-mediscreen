/**
 * 
 */
package com.tipikae.mediscreenUI.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
import org.springframework.web.bind.annotation.RequestMapping;

import com.tipikae.mediscreenUI.client.INoteServiceClient;
import com.tipikae.mediscreenUI.client.IPatientServiceClient;
import com.tipikae.mediscreenUI.dto.NewPatientDTO;
import com.tipikae.mediscreenUI.dto.UpdatePatientDTO;
import com.tipikae.mediscreenUI.exception.BadRequestException;
import com.tipikae.mediscreenUI.exception.PatientAlreadyExistException;
import com.tipikae.mediscreenUI.exception.PatientNotFoundException;
import com.tipikae.mediscreenUI.model.Patient;

/**
 * Controller for Mediscreeen-UI.
 * @author tipikae
 * @version 1.0
 *
 */
@Controller
@RequestMapping("/patient")
public class PatientController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PatientController.class);
	
	@Autowired
	private IPatientServiceClient patientClient;
	
	@Autowired
	private INoteServiceClient noteClient;
	
	/**
	 * Get all patients list.
	 * @param model Model
	 * @return String
	 */
	@GetMapping("/all")
	public String getAllPatients(Model model) {
		LOGGER.info("Getting all patients");
		try {
			model.addAttribute("patients", patientClient.getPatients());
			return "patient/list";
		} catch (Exception e) {
			log("getAllPatients", e);
			return "error/400";
		}
	}
	
	/**
	 * Get a patient.
	 * @param id long
	 * @param model Model
	 * @return String
	 */
	@GetMapping("/{id}")
	public String getPatient(@PathVariable("id") @NotNull @Positive long id, Model model) {
		LOGGER.info("Getting patient with id=" + id);
		try {
			model.addAttribute("patient", patientClient.getPatient(id));
			model.addAttribute("notes", noteClient.getPatientNotes(id));
			return "patient/get";
		} catch (PatientNotFoundException e) {
			log("getPatient", e);
			return "error/404";
		} catch (Exception e) {
			log("getPatient", e);
			return "error/400";
		}
	}
	
	/**
	 * Get add form.
	 * @param model Model
	 * @return String
	 */
	@GetMapping("/add")
	public String showAddForm(Model model) {
		LOGGER.info("Getting add patient form");
		if(!model.containsAttribute("patient")) {
    		model.addAttribute("patient", new Patient());
    	}
		
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
		LOGGER.info("Adding a new patient");
		if(result.hasErrors()) {
    		StringBuilder sb = new StringBuilder();
    		result.getAllErrors().stream().forEach(e -> sb.append(e.getDefaultMessage() + " "));
			LOGGER.debug("addPatient: has errors:" + sb);
			return "patient/add";
    	}
		
		try {
			model.addAttribute("patient", patientClient.addPatient(newPatientDTO));
			return "redirect:/patient/all?success=New patient added.";
		} catch (PatientAlreadyExistException e) {
			log("addPatient", e);
			return "redirect:/patient/all?error=Patient already exists.";
		} catch (BadRequestException e) {
			log("addPatient", e);
			return "redirect:/patient/all?error=Request error.";
		} catch (Exception e) {
			log("addPatient", e);
			return "redirect:/patient/all?error=An error occured.";
		}
	}
	
	/**
	 * Get update form.
	 * @param id long
	 * @param model Model
	 * @return String
	 */
	@GetMapping("/update/{id}")
	public String showUpdateForm(@PathVariable("id") @NotNull @Positive long id, Model model) {
		LOGGER.info("Getting update form for patient with id=" + id);
		try {
			model.addAttribute("patient", patientClient.getPatient(id));
			return "patient/update";
		} catch (PatientNotFoundException e) {
			log("showUpdateForm", e);
			return "error/404";
		} catch (Exception e) {
			log("showUpdateForm", e);
			return "error/400";
		}
	}
	
	/**
	 * Update a	patient.
	 * @param id long
	 * @param updatePatientDTO UpdatePatientDTO
	 * @param result BindingResult
	 * @param model Model
	 * @return String
	 */
	@PostMapping("/update/{id}")
	public String updatePatient(
    		@PathVariable("id") @NotNull @Positive long id, 
    		@ModelAttribute("patient") @Valid UpdatePatientDTO updatePatientDTO,
            BindingResult result, 
    		Model model) {
		LOGGER.info("Updating a patient with id=" + id);
		if(result.hasErrors()) {
    		StringBuilder sb = new StringBuilder();
    		result.getAllErrors().stream().forEach(e -> sb.append(e.getDefaultMessage() + " "));
			LOGGER.debug("updatePatient: has errors:" + sb);
			return "redirect:/patient/update/" + id + "?error=" + sb;
    	}
		
		try {
			patientClient.updatePatient(id, updatePatientDTO);
			return "redirect:/patient/" + id + "?success=Patient updated.";
		} catch (PatientNotFoundException e) {
			log("updatePatient", e);
			return "redirect:/patient/all?error=Patient not found.";
		} catch (BadRequestException e) {
			log("updatePatient", e);
			return "redirect:/patient/all?error=Request error.";
		} catch (Exception e) {
			log("updatePatient", e);
			return "redirect:/patient/all?error=An error occured.";
		}
	}
	
	@GetMapping("/delete/{id}")
	public String deletePatient(@PathVariable("id") @NotNull @Positive long id) {
		LOGGER.info("Deleting patient with id=" + id);
		try {
			patientClient.deletePatient(id);
			return "redirect:/patient/all?success=Patient deleted.";
		} catch (PatientNotFoundException e) {
			log("deletePatient", e);
			return "redirect:/patient/all?error=Patient not found.";
		} catch (BadRequestException e) {
			log("deletePatient", e);
			return "redirect:/patient/all?error=Request error.";
		} catch (Exception e) {
			log("deletePatient", e);
			return "redirect:/patient/all?error=An error occured.";
		}
	}
	
	private void log(String method, Exception e) {
		LOGGER.debug(method + ": " + e.getClass().getSimpleName() + ": " + e.getMessage());
	}
}