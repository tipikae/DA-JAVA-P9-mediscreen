package com.tipikae.assessmentservice.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.tipikae.assessmentservice.controller.AssessmentServiceController;
import com.tipikae.assessmentservice.service.IAssessmentServiceService;

@WebMvcTest(controllers = AssessmentServiceController.class)
class AssessmentServiceControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IAssessmentServiceService assessmentService;

	@Test
	void assessByIdReturns200AndAssessmentWhenOk() {
		
	}

	@Test
	void assessByIdReturns404WhenPatientNotFound() {
		
	}

	@Test
	void assessByIdReturns400WhenBadRequest() {
		
	}

	@Test
	void assessByFamilyNameReturns200AndListWhenOk() {
		
	}

	@Test
	void assessByFamilyNameReturns400WhenBadRequest() {
		
	}

}
