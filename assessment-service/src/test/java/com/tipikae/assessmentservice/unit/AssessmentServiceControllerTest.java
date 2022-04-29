package com.tipikae.assessmentservice.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.isA;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.tipikae.assessmentservice.controller.AssessmentServiceController;
import com.tipikae.assessmentservice.dto.AssessmentByFamilyDTO;
import com.tipikae.assessmentservice.dto.AssessmentByIdDTO;
import com.tipikae.assessmentservice.dto.AssessmentDTO;
import com.tipikae.assessmentservice.exception.BadRequestException;
import com.tipikae.assessmentservice.exception.PatientNotFoundException;
import com.tipikae.assessmentservice.service.IAssessmentServiceService;

@WebMvcTest(controllers = AssessmentServiceController.class)
class AssessmentServiceControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IAssessmentServiceService assessmentService;
	
	private static final String ROOT = "/assess";
	
	private static long patId = 1L;
	private static String message = "message";
	private static String family = "family";

	@Test
	void assessByIdReturns200AndAssessmentWhenOk() throws Exception {
		when(assessmentService.assessDiabetesById(any(AssessmentByIdDTO.class)))
			.thenReturn(new AssessmentDTO(message));
		mockMvc.perform(post(ROOT + "/id")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"patId\": " + patId + "}"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.message", is(message)));
	}

	@Test
	void assessByIdReturns404WhenPatientNotFound() throws Exception {
		doThrow(PatientNotFoundException.class)
			.when(assessmentService).assessDiabetesById(any(AssessmentByIdDTO.class));
		mockMvc.perform(post(ROOT + "/id")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"patId\": " + patId + "}"))
			.andExpect(status().is(404));
	}

	@Test
	void assessByIdReturns400WhenBadRequest() throws Exception {
		doThrow(BadRequestException.class)
			.when(assessmentService).assessDiabetesById(any(AssessmentByIdDTO.class));
		mockMvc.perform(post(ROOT + "/id")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"patId\": " + patId + "}"))
			.andExpect(status().is(400));
	}

	@Test
	void assessByIdReturns400WhenBadField() throws Exception {
		mockMvc.perform(post(ROOT + "/id")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"patId\": 0}"))
			.andExpect(status().is(400));
	}

	@Test
	void assessByFamilyNameReturns200AndListWhenOk() throws Exception {
		when(assessmentService.assessDiabetesByFamilyName(any(AssessmentByFamilyDTO.class)))
			.thenReturn(new ArrayList<>());
		mockMvc.perform(post(ROOT + "/familyName")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"familyName\": \"" + family + "\"}"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", isA(ArrayList.class)));
	}

	@Test
	void assessByFamilyNameReturns400WhenBadRequest() throws Exception {
		doThrow(BadRequestException.class)
			.when(assessmentService).assessDiabetesByFamilyName(any(AssessmentByFamilyDTO.class));
		mockMvc.perform(post(ROOT + "/familyName")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"familyName\": \"" + family + "\"}"))
			.andExpect(status().is(400));
	}

	@Test
	void assessByFamilyNameReturns400WhenBadField() throws Exception {
		mockMvc.perform(post(ROOT + "/familyName")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"familyName\": \"\"}"))
			.andExpect(status().is(400));
	}

}
