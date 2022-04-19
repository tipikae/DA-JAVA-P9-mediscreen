/**
 * 
 */
package com.tipikae.patientservice.service;

import java.util.List;

import com.tipikae.patientservice.dto.NewPatientDTO;
import com.tipikae.patientservice.dto.PatientDTO;
import com.tipikae.patientservice.dto.UpdatePatientDTO;
import com.tipikae.patientservice.exception.PatientAlreadyExists;
import com.tipikae.patientservice.exception.PatientNotFoundException;

/**
 * Patient service.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IPatientService {

	/**
	 * Add a new patient.
	 * @param newPatientDTO NewPatientDTO
	 * @return PatientDTO
	 * @throws PatientAlreadyExists
	 */
	PatientDTO addPatient(NewPatientDTO newPatientDTO) throws PatientAlreadyExists;
	
	/**
	 * Get a patient by its id.
	 * @param id int
	 * @return PatientDTO
	 * @throws PatientNotFoundException
	 */
	PatientDTO getPatientById(int id) throws PatientNotFoundException;
	
	/**
	 * Get all patients.
	 * @return List
	 */
	List<PatientDTO> getAllPatients();
	
	/**
	 * Update a patient.
	 * @param id int
	 * @param updatePatientDTO UpdatePatientDTO
	 * @throws PatientNotFoundException
	 */
	void updatePatient(int id, UpdatePatientDTO updatePatientDTO) throws PatientNotFoundException;
}
