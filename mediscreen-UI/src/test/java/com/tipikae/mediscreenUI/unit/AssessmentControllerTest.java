package com.tipikae.mediscreenUI.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.tipikae.mediscreenUI.controller.AssessmentController;
import com.tipikae.mediscreenUI.dto.AssessmentByFamilyDTO;
import com.tipikae.mediscreenUI.dto.AssessmentByIdDTO;
import com.tipikae.mediscreenUI.exception.NotFoundException;
import com.tipikae.mediscreenUI.model.Assessment;
import com.tipikae.mediscreenUI.security.MyAuthenticationFailureHandler;
import com.tipikae.mediscreenUI.security.MyAuthenticationProvider;
import com.tipikae.mediscreenUI.security.MyLogoutHandler;
import com.tipikae.mediscreenUI.service.IAssessmentService;

@WebMvcTest(controllers = AssessmentController.class)
@WithMockUser
class AssessmentControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IAssessmentService assessmentService;
	
	@MockBean
	private MyAuthenticationProvider authenticationProvider;
	
	@MockBean
	private MyAuthenticationFailureHandler authenticationFailureHandler;
	
	@MockBean
	private MyLogoutHandler logoutHandler;
	
	private static final String ROOT = "/assess";
	
	private static String message = "message";

	@Test
	void getAssessmentByIdReturns200AssessmentWhenOk() throws Exception {
		when(assessmentService.getAssessmentById(any(AssessmentByIdDTO.class)))
			.thenReturn(new Assessment(message));
		mockMvc.perform(get(ROOT + "/id/1"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("assessment"))
			.andExpect(view().name("patient/get :: #assessmentById"));
	}

	@Test
	void getAssessmentByIdReturns200ErrorWhenNotFound() throws Exception {
		String expected = "Error: patient not found.";
		Assessment assessment = new Assessment(expected);
		doThrow(NotFoundException.class)
			.when(assessmentService).getAssessmentById(any(AssessmentByIdDTO.class));
		mockMvc.perform(get(ROOT + "/id/1"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("assessment"))
			.andExpect(model().attribute("assessment", is(assessment)))
			.andExpect(view().name("patient/get :: #assessmentById"));
	}

	@Test
	void getAssessmentByFamilyReturns200ListWhenOk() throws Exception {
		when(assessmentService.getAssessmentsByFamily(any(AssessmentByFamilyDTO.class)))
			.thenReturn(List.of(new Assessment(message)));
		mockMvc.perform(get(ROOT + "/familyName/pouet"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("assessments"))
			.andExpect(view().name("patient/get :: #assessmentByFamily"));
	}

	@Test
	void getAssessmentByFamilyReturns200ErrorWhenEmpty() throws Exception {
		String expected = "No family members.";
		List<Assessment> assessments = List.of(new Assessment(expected));
		when(assessmentService.getAssessmentsByFamily(any(AssessmentByFamilyDTO.class)))
			.thenReturn(List.of());
		mockMvc.perform(get(ROOT + "/familyName/pouet"))
			.andExpect(status().isOk())
			.andExpect(model().attributeExists("assessments"))
			.andExpect(model().attribute("assessments", is(assessments)))
			.andExpect(view().name("patient/get :: #assessmentByFamily"));
	}

}
