/**
 * 
 */
package com.tipikae.assessmentservice.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tipikae.assessmentservice.assessment.Gender;
import com.tipikae.assessmentservice.assessment.IProcessData;
import com.tipikae.assessmentservice.assessment.IViewResult;
import com.tipikae.assessmentservice.client.INoteServiceClient;
import com.tipikae.assessmentservice.client.IPatientServiceClient;
import com.tipikae.assessmentservice.converterDTO.IConverterAssessmentDTO;
import com.tipikae.assessmentservice.dto.AssessmentByFamilyDTO;
import com.tipikae.assessmentservice.dto.AssessmentByIdDTO;
import com.tipikae.assessmentservice.dto.AssessmentDTO;
import com.tipikae.assessmentservice.exception.BadRequestException;
import com.tipikae.assessmentservice.exception.HttpClientException;
import com.tipikae.assessmentservice.exception.PatientNotFoundException;
import com.tipikae.assessmentservice.model.Assessment;
import com.tipikae.assessmentservice.model.Note;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.util.IUtil;

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
	private IPatientServiceClient patientClient;
	
	@Autowired
	private INoteServiceClient noteClient;
	
	@Autowired
	private IProcessData processData;
	
	@Autowired
	private IViewResult viewResult;
	
	@Autowired
	private IUtil util;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AssessmentDTO assessDiabetesById(AssessmentByIdDTO assessmentByIdDTO)
			throws PatientNotFoundException, BadRequestException, HttpClientException {
		LOGGER.debug("assessDiabetesById: id=" + assessmentByIdDTO.getPatId());
		Patient patient = patientClient.getPatientById(assessmentByIdDTO.getPatId());
		List<Note> notes = noteClient.getPatientNotes(assessmentByIdDTO.getPatId());
		
		return assessmentConverter.convertModelToDTO(getAssessment(patient, notes));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<AssessmentDTO> assessDiabetesByFamilyName(AssessmentByFamilyDTO assessmentByFamilyDTO)
			throws BadRequestException, HttpClientException {
		List<Patient> patients = patientClient.getPatientsByFamilyName(assessmentByFamilyDTO.getFamilyName());
		LOGGER.debug("assessDiabetesByFamilyName: family=" + assessmentByFamilyDTO.getFamilyName() 
			+ ", size=" + patients.size());
		List<AssessmentDTO> assessmentDTOs = new ArrayList<>();
		
		patients.parallelStream().forEach(patient -> {
			try {
				List<Note> notes;
				notes = noteClient.getPatientNotes(patient.getId());
				assessmentDTOs.add(assessmentConverter.convertModelToDTO(getAssessment(patient, notes)));
			} catch (BadRequestException e) {
				LOGGER.debug("assessDiabetesByFamilyName: BadRequestException: " + e.getMessage());
				assessmentDTOs.add(assessmentConverter
						.convertModelToDTO(getError(patient, "BadRequestException")));
			} catch (HttpClientException e) {
				LOGGER.debug("assessDiabetesByFamilyName: HttpClientException: " + e.getMessage());
				assessmentDTOs.add(assessmentConverter
						.convertModelToDTO(getError(patient, "HttpClientException")));
			}
		});
		
		return assessmentDTOs;
	}
	
	// calculates risk and returns assessment
	private Assessment getAssessment(Patient patient, List<Note> notes) {
		LOGGER.debug("getAssessment: patId=" + patient.getId());
		int age = util.calculateAge(patient.getDob());
		
		Gender gender;
		if(patient.getSex() == 'M') {
			gender = Gender.MALE;
		} else {
			gender = Gender.FEMALE;
		}
		String result = processData.getRisk(age, gender, notes);
		
		return new Assessment(viewResult.getResultView(patient, age, result));
	}
	
	// returns assessment with error message
	private Assessment getError(Patient patient, String error) {
		LOGGER.debug("getError: patientId=" + patient.getId());
		
		return new Assessment(viewResult.getErrorView(patient, error));
	}

}
