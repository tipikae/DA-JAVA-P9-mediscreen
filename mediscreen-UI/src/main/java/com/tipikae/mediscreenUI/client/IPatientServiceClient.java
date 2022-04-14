/**
 * 
 */
package com.tipikae.mediscreenUI.client;

import java.util.List;

import com.tipikae.mediscreenUI.dto.NewPatientDTO;
import com.tipikae.mediscreenUI.dto.UpdatePatientDTO;
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
	 */
	@RequestLine("GET /")
	List<Patient> getPatients();
	
	/**
	 * Get a patient.
	 * @param id int
	 * @return Patient
	 */
	@RequestLine("GET /{id}")
	Patient getPatient(@Param("id") int id);
	
	/**
	 * Add new patient.
	 * @param newPatientDTO NewPatientDTO
	 * @return Patient
	 */
	@RequestLine("POST /")
    @Headers("Content-Type: application/json")
	Patient addPatient(NewPatientDTO newPatientDTO);
	
	/**
	 * Update a patient.
	 * @param id int
	 * @param updatePatientDTO UpdatePatientDTO
	 */
	@RequestLine("PUT /{id}")
    @Headers("Content-Type: application/json")
	void updatePatient(@Param("id") int id, UpdatePatientDTO updatePatientDTO);
}
