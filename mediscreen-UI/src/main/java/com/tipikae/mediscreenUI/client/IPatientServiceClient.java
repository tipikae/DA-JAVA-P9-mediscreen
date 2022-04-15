/**
 * 
 */
package com.tipikae.mediscreenUI.client;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

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
	 * @return CollectionModel
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	@RequestLine("GET /patients")
	CollectionModel<Patient> getPatients() throws BadRequestException, HttpClientException;
	
	/**
	 * Get a patient.
	 * @param id Integer
	 * @return EntityModel
	 * @throws PatientNotFoundException
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	@RequestLine("GET /patients/{id}")
	EntityModel<Patient> getPatient(@Param("id") Integer id) 
			throws PatientNotFoundException, BadRequestException, HttpClientException;
	
	/**
	 * Add new patient.
	 * @param newPatientDTO NewPatientDTO
	 * @return EntityModel
	 * @throws PatientAlreadyExistException
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	@RequestLine("POST /patients")
    @Headers("Content-Type: application/json")
	EntityModel<Patient> addPatient(NewPatientDTO newPatientDTO) 
			throws PatientAlreadyExistException, BadRequestException, HttpClientException;
	
	/**
	 * Update a patient.
	 * @param id Integer
	 * @param updatePatientDTO UpdatePatientDTO
	 * @throws PatientNotFoundException
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	@RequestLine("PUT /patients/{id}")
    @Headers("Content-Type: application/json")
	void updatePatient(@Param("id") Integer id, UpdatePatientDTO updatePatientDTO) 
			 throws PatientNotFoundException, BadRequestException, HttpClientException;
}
