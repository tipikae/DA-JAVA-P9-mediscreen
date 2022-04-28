package com.tipikae.assessmentservice.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
import com.tipikae.assessmentservice.service.AssessmentServiceServiceImpl;
import com.tipikae.assessmentservice.util.IUtil;
import com.tipikae.assessmentservice.validation.Gender;

@ExtendWith(MockitoExtension.class)
class AssessmentServiceServiceTest {
	
	@Mock
	private IConverterAssessmentDTO assessmentConverter;
	
	@Mock
	private IPatientServiceClient patientClient;
	
	@Mock
	private INoteServiceClient noteClient;
	
	@Mock
	private IProcessData processData;
	
	@Mock
	private IViewResult viewResult;
	
	@Mock
	private IUtil util;
	
	@InjectMocks
	private AssessmentServiceServiceImpl assessmentService;
	
	private static long patId;
	private static String family;
	private static String given;
	private static LocalDate dob;
	private static char sex;
	private static int age;
	private static String message;
	private static Patient patient;
	private static Note note;
	private static AssessmentByIdDTO assessmentByIdDTO;
	private static AssessmentByFamilyDTO assessmentByFamilyDTO;
	private static AssessmentDTO assessmentDTO;
	private static List<Note> notes;
	private static List<Patient> patients;
	
	@BeforeAll
	private static void setUp() {
		patId = 1L;
		family = "family";
		given = "given";
		dob = LocalDate.of(2000, 01, 01);
		sex = 'M';
		age = 22;
		message = "message";
		patient = new Patient(patId, family, given, dob, sex, "address", "phone");
		note = new Note("id", patId, dob, "e");
		assessmentByIdDTO = new AssessmentByIdDTO(patId);
		assessmentByFamilyDTO = new AssessmentByFamilyDTO(family);
		assessmentDTO = new AssessmentDTO(message);
		notes = List.of(note);
		patients = List.of(patient);
	}

	@Test
	void assessDiabetesByIdReturnsAssessmentWhenOk() 
			throws PatientNotFoundException, BadRequestException, HttpClientException {
		when(patientClient.getPatientById(anyLong())).thenReturn(patient);
		when(noteClient.getPatientNotes(anyLong())).thenReturn(notes);
		when(util.calculateAge(any(LocalDate.class))).thenReturn(age);
		when(processData.getRisk(anyInt(), any(Gender.class), anyList())).thenReturn(message);
		when(assessmentConverter.convertModelToDTO(any(Assessment.class))).thenReturn(assessmentDTO);
		assertEquals(message, assessmentService.assessDiabetesById(assessmentByIdDTO).getMessage());
	}

	@Test
	void assessDiabetesByIdThrowsExceptionWhenPatientNotFound() 
			throws PatientNotFoundException, BadRequestException, HttpClientException {
		doThrow(PatientNotFoundException.class).when(patientClient).getPatientById(anyLong());
		assertThrows(PatientNotFoundException.class, 
				() -> assessmentService.assessDiabetesById(assessmentByIdDTO));
	}

	@Test
	void assessDiabetesByIdThrowsExceptionWhenBadRequest() 
			throws PatientNotFoundException, BadRequestException, HttpClientException {
		doThrow(BadRequestException.class).when(noteClient).getPatientNotes(anyLong());
		assertThrows(BadRequestException.class, 
				() -> assessmentService.assessDiabetesById(assessmentByIdDTO));
	}

	@Test
	void assessDiabetesByFamilyNameReturnsAssessmentWhenOk() 
			throws PatientNotFoundException, BadRequestException, HttpClientException {
		when(patientClient.getPatientsByFamilyName(anyString())).thenReturn(patients);
		when(noteClient.getPatientNotes(anyLong())).thenReturn(notes);
		when(util.calculateAge(any(LocalDate.class))).thenReturn(age);
		when(processData.getRisk(anyInt(), any(Gender.class), anyList())).thenReturn(message);
		when(assessmentConverter.convertModelToDTO(any(Assessment.class))).thenReturn(assessmentDTO);
		assertTrue(assessmentService.assessDiabetesByFamilyName(assessmentByFamilyDTO).size() > 0);
	}

}
