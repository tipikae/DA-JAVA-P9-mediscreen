package com.tipikae.mediscreenUI.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.tipikae.mediscreenUI.client.IAssessmentServiceClient;
import com.tipikae.mediscreenUI.controller.AssessmentController;

@WebMvcTest(controllers = AssessmentController.class)
class AssessmentControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IAssessmentServiceClient assessmentClient;

	@Test
	void getAssessmentByIdReturns200AssessmentWhenOk() {
		
	}

	@Test
	void getAssessmentByIdReturns200ErrorWhenNotFound() {
		
	}

	@Test
	void getAssessmentByFamilyReturns200ListWhenOk() {
		
	}

	@Test
	void getAssessmentByFamilyReturns200ErrorWhenEmpty() {
		
	}

}
