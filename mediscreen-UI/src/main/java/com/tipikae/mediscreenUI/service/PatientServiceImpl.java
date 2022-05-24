/**
 * 
 */
package com.tipikae.mediscreenUI.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tipikae.mediscreenUI.dto.NewPatientDTO;
import com.tipikae.mediscreenUI.dto.UpdatePatientDTO;
import com.tipikae.mediscreenUI.exception.BadRequestException;
import com.tipikae.mediscreenUI.exception.HttpClientException;
import com.tipikae.mediscreenUI.exception.AlreadyExistsException;
import com.tipikae.mediscreenUI.exception.NotFoundException;
import com.tipikae.mediscreenUI.model.Patient;

/**
 * Patient service.
 * @author tipikae
 * @version 1.0
 *
 */
@Service
public class PatientServiceImpl implements IPatientService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PatientServiceImpl.class);
	
	private RestTemplate restTemplate;

	@Autowired
	public PatientServiceImpl(RestTemplateBuilder restTemplateBuilder) {
		restTemplate = restTemplateBuilder
		          .errorHandler(new ClientErrorHandler())
		          .build();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<Patient> getPatients(int page, int size) throws BadRequestException, HttpClientException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Patient getPatient(long id) throws NotFoundException, BadRequestException, HttpClientException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Patient addPatient(NewPatientDTO newPatientDTO)
			throws AlreadyExistsException, BadRequestException, HttpClientException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updatePatient(long id, UpdatePatientDTO updatePatientDTO)
			throws NotFoundException, BadRequestException, HttpClientException {
		// TODO Auto-generated method stub

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deletePatient(long id) throws NotFoundException, BadRequestException, HttpClientException {
		// TODO Auto-generated method stub

	}

}
