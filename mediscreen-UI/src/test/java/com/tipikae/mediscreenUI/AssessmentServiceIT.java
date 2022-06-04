package com.tipikae.mediscreenUI;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.tipikae.mediscreenUI.dto.AssessmentByFamilyDTO;
import com.tipikae.mediscreenUI.dto.AssessmentByIdDTO;
import com.tipikae.mediscreenUI.exception.NotFoundException;
import com.tipikae.mediscreenUI.model.Assessment;
import com.tipikae.mediscreenUI.security.MyAuthentication;
import com.tipikae.mediscreenUI.security.MyLogout;
import com.tipikae.mediscreenUI.service.IAssessmentService;

@SpringBootTest
class AssessmentServiceIT {
	
	@Autowired
	private IAssessmentService assessmentService;
	
	@Autowired
	private MyAuthentication myAuthentication;
	
	@Autowired
	private MyLogout myLogout;
	
	private String username = "user1";
	private String password = "user1";
	
	private void authenticate() {
		Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
		myAuthentication.authenticate(authentication);
	}

	@Test
	void test() throws NotFoundException {
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
		
		// authentication
		authenticate();
		
		// assessmentById
		assertTrue(assessmentService.getAssessmentById(idNoneDTO).getMessage().endsWith(none));
		assertTrue(assessmentService.getAssessmentById(idBorderlineDTO).getMessage().endsWith(borderline));
		assertTrue(assessmentService.getAssessmentById(idInDangerDTO).getMessage().endsWith(inDanger));
		assertTrue(assessmentService.getAssessmentById(idEarlyOnsetDTO).getMessage().endsWith(earlyOnset));
		
		// assessmentByFamily
		// family none
		List<Assessment> assessmentsNone = (List<Assessment>)assessmentService.getAssessmentsByFamily(familyNoneDTO);
		assertTrue(assessmentsNone.size() > 0);
		// family borderline
		List<Assessment> assessmentsBorderline = assessmentService.getAssessmentsByFamily(familyBorderlineDTO);
		assertTrue(assessmentsBorderline.size() > 0);
		// family inDanger
		List<Assessment> assessmentsInDanger = assessmentService.getAssessmentsByFamily(familyInDangerDTO);
		assertTrue(assessmentsInDanger.size() > 0);
		// family earlyOnset
		List<Assessment> assessmentsEarlyOnset = assessmentService.getAssessmentsByFamily(familyEarlyOnsetDTO);
		assertTrue(assessmentsEarlyOnset.size() > 0);
		
		// logout
		myLogout.logout();
	}

}
