/**
 * 
 */
package com.tipikae.mediscreenUI.client;

import java.util.List;

import com.tipikae.mediscreenUI.dto.NewPatientDTO;
import com.tipikae.mediscreenUI.dto.UpdatePatientDTO;
import com.tipikae.mediscreenUI.exception.BadRequestException;
import com.tipikae.mediscreenUI.exception.HttpClientException;
import com.tipikae.mediscreenUI.exception.PatientAlreadyExistException;
import com.tipikae.mediscreenUI.exception.PatientNotFoundException;
import com.tipikae.mediscreenUI.model.Patient;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * Feign client for Patient Service.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IPatientServiceClient {

	/**
	 * Get patients list.
	 * @return List
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	@RequestLine("GET /")
	List<Patient> getPatients() throws BadRequestException, HttpClientException;
	
	/**
	 * Get a patient.
	 * @param id Integer
	 * @return Patient
	 * @throws PatientNotFoundException
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	@RequestLine("GET /{id}")
	Patient getPatient(@Param("id") Integer id) 
			throws PatientNotFoundException, BadRequestException, HttpClientException;
	
	/**
	 * Add new patient.
	 * @param newPatientDTO NewPatientDTO
	 * @return Patient
	 * @throws PatientAlreadyExistException
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	@RequestLine("POST /")
    @Headers("Content-Type: application/json")
	Patient addPatient(NewPatientDTO newPatientDTO) 
			throws PatientAlreadyExistException, BadRequestException, HttpClientException;
	
	/**
	 * Update a patient.
	 * @param id Integer
	 * @param updatePatientDTO UpdatePatientDTO
	 * @throws PatientNotFoundException
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	@RequestLine("PUT /{id}")
    @Headers("Content-Type: application/json")
	void updatePatient(@Param("id") Integer id, UpdatePatientDTO updatePatientDTO) 
			 throws PatientNotFoundException, BadRequestException, HttpClientException;
}
