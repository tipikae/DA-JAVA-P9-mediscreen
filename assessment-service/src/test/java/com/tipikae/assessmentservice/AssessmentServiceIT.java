package com.tipikae.assessmentservice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.assessmentservice.assessment.Risk;
import com.tipikae.assessmentservice.dto.AssessmentByFamilyDTO;
import com.tipikae.assessmentservice.dto.AssessmentByIdDTO;
import com.tipikae.assessmentservice.exception.BadRequestException;
import com.tipikae.assessmentservice.exception.HttpClientException;
import com.tipikae.assessmentservice.exception.PatientNotFoundException;
import com.tipikae.assessmentservice.service.IAssessmentServiceService;

@SpringBootTest
class AssessmentServiceIT {
	
	@Autowired
	private IAssessmentServiceService assessmentService;

	@Test
	void test() throws PatientNotFoundException, BadRequestException, HttpClientException {
		long idNone = 1L;
		long idBorderline = 2L;
		long idInDanger = 3L;
		long idEarlyOnset = 4L;
		String familyNone = "TestNone";
		String familyBorderline = "TestBorderline";
		String familyInDanger = "TestInDanger";
		String familyEarlyOnset = "TestEarlyOnset";
		
		AssessmentByIdDTO idNoneDTO = new AssessmentByIdDTO(idNone);
		AssessmentByIdDTO idBorderlineDTO = new AssessmentByIdDTO(idBorderline);
		AssessmentByIdDTO idInDangerDTO = new AssessmentByIdDTO(idInDanger);
		AssessmentByIdDTO idEarlyOnsetDTO = new AssessmentByIdDTO(idEarlyOnset);
		
		AssessmentByFamilyDTO familyNoneDTO = new AssessmentByFamilyDTO(familyNone);
		AssessmentByFamilyDTO familyBorderlineDTO = new AssessmentByFamilyDTO(familyBorderline);
		AssessmentByFamilyDTO familyInDangerDTO = new AssessmentByFamilyDTO(familyInDanger);
		AssessmentByFamilyDTO familyEarlyOnsetDTO = new AssessmentByFamilyDTO(familyEarlyOnset);
		
		assertTrue(assessmentService.assessDiabetesById(idNoneDTO).getMessage()
				.endsWith(Risk.NONE.getLabel()));
		assertTrue(assessmentService.assessDiabetesById(idBorderlineDTO).getMessage()
				.endsWith(Risk.BORDERLINE.getLabel()));
		assertTrue(assessmentService.assessDiabetesById(idInDangerDTO).getMessage()
				.endsWith(Risk.INDANGER.getLabel()));
		assertTrue(assessmentService.assessDiabetesById(idEarlyOnsetDTO).getMessage()
				.endsWith(Risk.EARLYONSET.getLabel()));
		
		assertTrue(assessmentService.assessDiabetesByFamilyName(familyNoneDTO).get(0).getMessage()
				.endsWith(Risk.NONE.getLabel()));
		assertTrue(assessmentService.assessDiabetesByFamilyName(familyBorderlineDTO).get(0).getMessage()
				.endsWith(Risk.BORDERLINE.getLabel()));
		assertTrue(assessmentService.assessDiabetesByFamilyName(familyInDangerDTO).get(0).getMessage()
				.endsWith(Risk.INDANGER.getLabel()));
		assertTrue(assessmentService.assessDiabetesByFamilyName(familyEarlyOnsetDTO).get(0).getMessage()
				.endsWith(Risk.EARLYONSET.getLabel()));
	}

}
