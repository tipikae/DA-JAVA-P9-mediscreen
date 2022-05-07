package com.tipikae.assessmentservice.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

import com.tipikae.assessmentservice.client.INoteServiceClient;
import com.tipikae.assessmentservice.client.IPatientServiceClient;
import com.tipikae.assessmentservice.dto.AssessmentByFamilyDTO;
import com.tipikae.assessmentservice.dto.AssessmentByIdDTO;
import com.tipikae.assessmentservice.dto.AssessmentDTO;
import com.tipikae.assessmentservice.dto.IConverterAssessmentDTO;
import com.tipikae.assessmentservice.exception.BadRequestException;
import com.tipikae.assessmentservice.exception.HttpClientException;
import com.tipikae.assessmentservice.exception.PatientNotFoundException;
import com.tipikae.assessmentservice.model.Assessment;
import com.tipikae.assessmentservice.model.Note;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.model.Risk;
import com.tipikae.assessmentservice.risk.IRiskCalculator;
import com.tipikae.assessmentservice.risk.IViewResult;
import com.tipikae.assessmentservice.service.AgeProvider;
import com.tipikae.assessmentservice.service.AssessmentServiceServiceImpl;

@ExtendWith(MockitoExtension.class)
class AssessmentServiceServiceTest {
	
	@Mock
	private IConverterAssessmentDTO assessmentConverter;
	
	@Mock
	private IPatientServiceClient patientClient;
	
	@Mock
	private INoteServiceClient noteClient;
	
	@Mock
	private IRiskCalculator riskCalculator;
	
	@Mock
	private IViewResult viewResult;
	
	@Mock
	private AgeProvider ageProvider;
	
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
	private static Risk risk;
	
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
		risk = new Risk(1L, message);
	}

	@Test
	void assessDiabetesByIdReturnsAssessmentWhenOk() 
			throws Exception {
		when(patientClient.getPatientById(anyLong())).thenReturn(patient);
		when(noteClient.getPatientNotes(anyLong())).thenReturn(notes);
		when(ageProvider.calculateAge(any(LocalDate.class))).thenReturn(age);
		when(riskCalculator.calculateRisk(any(Patient.class))).thenReturn(risk);
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
			throws Exception {
		when(patientClient.getPatientsByFamilyName(anyString())).thenReturn(patients);
		when(noteClient.getPatientNotes(anyLong())).thenReturn(notes);
		when(ageProvider.calculateAge(any(LocalDate.class))).thenReturn(age);
		when(riskCalculator.calculateRisk(any(Patient.class))).thenReturn(risk);
		when(assessmentConverter.convertModelToDTO(any(Assessment.class))).thenReturn(assessmentDTO);
		assertTrue(assessmentService.assessDiabetesByFamilyName(assessmentByFamilyDTO).size() > 0);
	}

}
