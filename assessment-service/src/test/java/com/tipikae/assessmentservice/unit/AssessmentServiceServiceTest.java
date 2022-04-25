package com.tipikae.assessmentservice.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.assessmentservice.client.INoteServiceClient;
import com.tipikae.assessmentservice.client.IPatientServiceClient;
import com.tipikae.assessmentservice.converterDTO.IConverterAssessmentDTO;
import com.tipikae.assessmentservice.service.AssessmentServiceServiceImpl;

@SpringBootTest
class AssessmentServiceServiceTest {
	
	@Mock
	private IConverterAssessmentDTO assessmentConverter;
	
	@Mock
	private IPatientServiceClient patientClient;
	
	@Mock
	private INoteServiceClient noteClient;
	
	@InjectMocks
	private AssessmentServiceServiceImpl assessmentService;

	@Test
	void assessDiabetesByIdReturnsAssessmentWhenOk() {
		
	}

	@Test
	void assessDiabetesByIdThrowsExceptionWhenPatientNotFound() {
		
	}

	@Test
	void assessDiabetesByIdThrowsExceptionWhenBadRequest() {
		
	}

	@Test
	void assessDiabetesByFamilyNameReturnsAssessmentWhenOk() {
		
	}

	@Test
	void assessDiabetesByFamilyNameThrowsExceptionWhenBadRequest() {
		
	}

}
