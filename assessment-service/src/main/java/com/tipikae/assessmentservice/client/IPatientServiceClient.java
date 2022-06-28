/**
 * 
 */
package com.tipikae.assessmentservice.client;

import java.util.List;

import com.tipikae.assessmentservice.exception.BadRequestException;
import com.tipikae.assessmentservice.exception.HttpClientException;
import com.tipikae.assessmentservice.exception.NotFoundException;
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
	 * @throws NotFoundException
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	@RequestLine("GET /patients/id/{id}")
	Patient getPatientById(@Param("id") long id) 
			throws NotFoundException, BadRequestException, HttpClientException;
	
	/**
	 * Get patients by family.
	 * @param familyName
	 * @return List
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	@RequestLine("GET /patients/familyName/{familyName}")
	List<Patient> getPatientsByFamilyName(@Param("familyName") String familyName) 
			throws BadRequestException, HttpClientException;
}
