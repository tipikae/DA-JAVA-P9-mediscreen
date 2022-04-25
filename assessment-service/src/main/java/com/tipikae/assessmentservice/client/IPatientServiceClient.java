/**
 * 
 */
package com.tipikae.assessmentservice.client;

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
	@RequestLine("GET /patients/{id}")
	Patient getPatient(@Param("id") long id) 
			throws PatientNotFoundException, BadRequestException, HttpClientException;
}
