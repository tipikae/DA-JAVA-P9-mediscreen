/**
 * 
 */
package com.tipikae.patientservice.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tipikae.patientservice.dto.NewPatientDTO;
import com.tipikae.patientservice.dto.PatientDTO;
import com.tipikae.patientservice.dto.UpdatePatientDTO;
import com.tipikae.patientservice.exception.PatientAlreadyExistsException;
import com.tipikae.patientservice.exception.PatientNotFoundException;
import com.tipikae.patientservice.service.IPatientServiceService;

/**
 * Patient service controller.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
@Validated
@RequestMapping("/patients")
public class PatientServiceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(PatientServiceController.class);
	
	@Autowired
	private IPatientServiceService patientService;
	
	/**
	 * Get all patients.
	 * @return ResponseEntity
	 */
	@GetMapping("/")
	public ResponseEntity<List<PatientDTO>> getAllPatients() {
		LOGGER.info("Getting all patients.");
		List<PatientDTO> patientDTOs = patientService.getAllPatients();
		
		return new ResponseEntity<List<PatientDTO>>(patientDTOs, HttpStatus.OK);
	}
	
	/**
	 * Get a patient by its id.
	 * @param id long
	 * @return ResponseEntity
	 * @throws PatientNotFoundException
	 */
	@GetMapping("/{id}")
	public ResponseEntity<PatientDTO> getPatient(@PathVariable("id") @NotNull @Positive long id) 
			throws PatientNotFoundException {
		LOGGER.info("Getting patient with id=" + id);
		PatientDTO patientDTO = patientService.getPatientById(id);
		
		return new ResponseEntity<PatientDTO>(patientDTO, HttpStatus.OK);
	}
	
	/**
	 * Add a new patient.
	 * @param newPatientDTO NewPatientDTO
	 * @return ResponseEntity
	 * @throws PatientAlreadyExistsException
	 */
	@PostMapping(value = "/", consumes = {"application/json"})
	public ResponseEntity<PatientDTO> addPatient(@RequestBody @Valid NewPatientDTO newPatientDTO) 
			throws PatientAlreadyExistsException {
		LOGGER.info("Adding patient");
		PatientDTO patientDTO = patientService.addPatient(newPatientDTO);
		
		return new ResponseEntity<PatientDTO>(patientDTO, HttpStatus.CREATED);
	}
	
	/**
	 * Update a patient.
	 * @param id long
	 * @param updatePatientDTO UpdatePatientDTO
	 * @return ResponseEntity
	 * @throws PatientNotFoundException
	 */
	@PutMapping(value = "/{id}", consumes = {"application/json"})
	public ResponseEntity<Object> updatePatient(
			@PathVariable @NotNull @Positive long id, 
			@RequestBody @Valid UpdatePatientDTO updatePatientDTO) 
			throws PatientNotFoundException {
		LOGGER.info("Updating patient with id=" + id);
		patientService.updatePatient(id, updatePatientDTO);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Delete a patient by its id.
	 * @param id long
	 * @return ResponseEntity
	 * @throws PatientNotFoundException
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deletePatient(@PathVariable @NotNull @Positive long id) 
			throws PatientNotFoundException {
		LOGGER.info("Deleting patient with id=" + id);
		patientService.deletePatient(id);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
