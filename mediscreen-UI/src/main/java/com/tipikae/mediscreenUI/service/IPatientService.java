/**
 * 
 */
package com.tipikae.mediscreenUI.service;

import org.springframework.data.domain.Page;

import com.tipikae.mediscreenUI.dto.NewPatientDTO;
import com.tipikae.mediscreenUI.dto.UpdatePatientDTO;
import com.tipikae.mediscreenUI.exception.BadRequestException;
import com.tipikae.mediscreenUI.exception.HttpClientException;
import com.tipikae.mediscreenUI.exception.AlreadyExistsException;
import com.tipikae.mediscreenUI.exception.NotFoundException;
import com.tipikae.mediscreenUI.model.Patient;

/**
 * Client PatientService
 * @author tipikae
 * @author tipikae 1.0
 *
 */
public interface IPatientService {

	/**
	 * Get patients list.
	 * @param page int
	 * @param size int
	 * @return Page
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	//@RequestLine("GET /patients/?page={page}&size={size}")
	Page<Patient> getPatients(int page, int size) 
			throws BadRequestException, HttpClientException;
	
	/**
	 * Get a patient.
	 * @param id long
	 * @return Patient
	 * @throws NotFoundException
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	//@RequestLine("GET /patients/id/{id}")
	Patient getPatient(long id) 
			throws NotFoundException, BadRequestException, HttpClientException;
	
	/**
	 * Add new patient.
	 * @param newPatientDTO NewPatientDTO
	 * @return Patient
	 * @throws AlreadyExistsException
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	//@RequestLine("POST /patients/")
    //@Headers("Content-Type: application/json")
	Patient addPatient(NewPatientDTO newPatientDTO) 
			throws AlreadyExistsException, BadRequestException, HttpClientException;
	
	/**
	 * Update a patient.
	 * @param id long
	 * @param updatePatientDTO UpdatePatientDTO
	 * @throws NotFoundException
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	//@RequestLine("PUT /patients/{id}")
    //@Headers("Content-Type: application/json")
	void updatePatient(long id, UpdatePatientDTO updatePatientDTO) 
			 throws NotFoundException, BadRequestException, HttpClientException;
	
	/**
	 * Delete a patient.
	 * @param id long
	 * @throws NotFoundException
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	//@RequestLine("DELETE /patients/{id}")
	void deletePatient(long id) 
			 throws NotFoundException, BadRequestException, HttpClientException;
}
