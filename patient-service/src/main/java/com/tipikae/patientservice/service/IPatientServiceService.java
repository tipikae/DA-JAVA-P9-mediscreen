/**
 * 
 */
package com.tipikae.patientservice.service;

import java.util.List;

import com.tipikae.patientservice.dto.NewPatientDTO;
import com.tipikae.patientservice.dto.PatientDTO;
import com.tipikae.patientservice.dto.UpdatePatientDTO;
import com.tipikae.patientservice.exception.PatientAlreadyExistsException;
import com.tipikae.patientservice.exception.PatientNotFoundException;

/**
 * Patient service.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IPatientServiceService {

	/**
	 * Add a new patient.
	 * @param newPatientDTO NewPatientDTO
	 * @return PatientDTO
	 * @throws PatientAlreadyExistsException
	 */
	PatientDTO addPatient(NewPatientDTO newPatientDTO) throws PatientAlreadyExistsException;
	
	/**
	 * Get a patient by its id.
	 * @param id long
	 * @return PatientDTO
	 * @throws PatientNotFoundException
	 */
	PatientDTO getPatientById(long id) throws PatientNotFoundException;
	
	/**
	 * Get a patients list by family name.
	 * @param family String
	 * @return List
	 */
	List<PatientDTO> getPatientsByFamily(String family);
	
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
	void updatePatient(long id, UpdatePatientDTO updatePatientDTO) throws PatientNotFoundException;
	
	/**
	 * Delete a patient by its id.
	 * @param id long
	 * @throws PatientNotFoundException
	 */
	void deletePatient(long id) throws PatientNotFoundException;
}
