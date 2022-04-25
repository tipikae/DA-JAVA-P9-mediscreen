/**
 * 
 */
package com.tipikae.assessmentservice.client;

import java.util.List;

import com.tipikae.assessmentservice.exception.BadRequestException;
import com.tipikae.assessmentservice.exception.HttpClientException;
import com.tipikae.assessmentservice.exception.PatientNotFoundException;
import com.tipikae.assessmentservice.model.Patient;

import feign.Param;
import feign.RequestLine;

/**
 * Feign client for patient-service.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IPatientServiceClient {

	/**
	 * Get a patient.
	 * @param id long
	 * @return Patient
	 * @throws PatientNotFoundException
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	@RequestLine("GET /patients/id/{id}")
	Patient getPatientById(@Param("id") long id) 
			throws PatientNotFoundException, BadRequestException, HttpClientException;
	
	@RequestLine("GET /patients/family/{family}")
	List<Patient> getPatientsByFamilyName(@Param("family") String family) 
			throws BadRequestException, HttpClientException;
}
