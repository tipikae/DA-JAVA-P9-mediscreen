/**
 * 
 */
package com.tipikae.assessmentservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tipikae.assessmentservice.client.INoteServiceClient;
import com.tipikae.assessmentservice.client.IPatientServiceClient;
import com.tipikae.assessmentservice.converterDTO.IConverterAssessmentDTO;
import com.tipikae.assessmentservice.dto.AssessmentByFamilyDTO;
import com.tipikae.assessmentservice.dto.AssessmentByIdDTO;
import com.tipikae.assessmentservice.dto.AssessmentDTO;
import com.tipikae.assessmentservice.exception.BadRequestException;
import com.tipikae.assessmentservice.exception.HttpClientException;
import com.tipikae.assessmentservice.exception.PatientNotFoundException;

/**
 * Assessment service service.
 * @author tipikae
 * @version 1.0
 *
 */
@Service
public class AssessmentServiceServiceImpl implements IAssessmentServiceService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AssessmentServiceServiceImpl.class);
	
	@Autowired
	private IConverterAssessmentDTO assessmentConverter;
	
	@Autowired
	private IPatientServiceClient patientService;
	
	@Autowired
	private INoteServiceClient noteClient;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AssessmentDTO assessDiabetesById(AssessmentByIdDTO dto)
			throws PatientNotFoundException, BadRequestException, HttpClientException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AssessmentDTO> assessDiabetesByFamilyName(AssessmentByFamilyDTO dto)
			throws BadRequestException, HttpClientException {
		// TODO Auto-generated method stub
		return null;
	}

}
