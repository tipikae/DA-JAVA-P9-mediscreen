package com.tipikae.mediscreenUI;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.mediscreenUI.client.IAssessmentServiceClient;
import com.tipikae.mediscreenUI.dto.AssessmentByFamilyDTO;
import com.tipikae.mediscreenUI.dto.AssessmentByIdDTO;
import com.tipikae.mediscreenUI.exception.PatientNotFoundException;
import com.tipikae.mediscreenUI.model.Assessment;

@SpringBootTest
class AssessmentServiceIT {
	
	@Autowired
	IAssessmentServiceClient assessmentClient;

	@Test
	void test() throws PatientNotFoundException {
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
		
		String none = "None";
		String borderline = "Borderline";
		String inDanger = "In Danger";
		String earlyOnset = "Early onset";
		
		assertTrue(assessmentClient.getAssessmentById(idNoneDTO).getMessage().endsWith(none));
		assertTrue(assessmentClient.getAssessmentById(idBorderlineDTO).getMessage().endsWith(borderline));
		assertTrue(assessmentClient.getAssessmentById(idInDangerDTO).getMessage().endsWith(inDanger));
		assertTrue(assessmentClient.getAssessmentById(idEarlyOnsetDTO).getMessage().endsWith(earlyOnset));
		
		// family none
		List<Assessment> assessmentsNone = assessmentClient.getAssessmentsByFamily(familyNoneDTO);
		assertTrue(assessmentsNone.size() == 1);
		assertTrue(assessmentsNone.get(0).getMessage().endsWith(none));
		
		// family borderline
		List<Assessment> assessmentsBorderline = assessmentClient.getAssessmentsByFamily(familyBorderlineDTO);
		assertTrue(assessmentsBorderline.size() == 1);
		assertTrue(assessmentsBorderline.get(0).getMessage().endsWith(borderline));
		
		// family inDanger
		List<Assessment> assessmentsInDanger = assessmentClient.getAssessmentsByFamily(familyInDangerDTO);
		assertTrue(assessmentsInDanger.size() == 1);
		assertTrue(assessmentsInDanger.get(0).getMessage().endsWith(inDanger));
		
		// family earlyOnset
		List<Assessment> assessmentsEarlyOnset = assessmentClient.getAssessmentsByFamily(familyEarlyOnsetDTO);
		assertTrue(assessmentsEarlyOnset.size() == 1);
		assertTrue(assessmentsEarlyOnset.get(0).getMessage().endsWith(earlyOnset));
	}

}
