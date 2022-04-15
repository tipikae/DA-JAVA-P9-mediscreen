package com.tipikae.mediscreenUI.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.test.web.servlet.MockMvc;

import com.tipikae.mediscreenUI.client.IPatientServiceClient;
import com.tipikae.mediscreenUI.controller.MediscreenUIController;
import com.tipikae.mediscreenUI.exception.BadRequestException;
import com.tipikae.mediscreenUI.exception.HttpClientException;
import com.tipikae.mediscreenUI.exception.PatientNotFoundException;
import com.tipikae.mediscreenUI.model.Patient;

@WebMvcTest(controllers = MediscreenUIController.class)
class MediscreenUIControllerTest {
	
	private static final String ROOT = "/patient"; 
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IPatientServiceClient patientClient;

	@Test
	void getAllPatientsReturnsListWhenOk() throws Exception {
		when(patientClient.getPatients()).thenReturn(CollectionModel.of(new ArrayList<>()));
		mockMvc.perform(get(ROOT + "/list"))
			.andExpect(status().isOk())
			.andExpect(view().name("patient/list"));
	}

	@Test
	void getAllPatientsReturns400WhenBadRequest() throws Exception {
		doThrow(BadRequestException.class).when(patientClient).getPatients();
		mockMvc.perform(get(ROOT + "/list"))
			.andExpect(status().isOk())
			.andExpect(view().name("error/400"));
	}

	@Test
	void getPatientReturnsPatientWhenOk() throws Exception {
		Patient patient = new Patient(1L, "family", "given", LocalDate.now(), 'F', "address", "phone");
		when(patientClient.getPatient(anyInt())).thenReturn(EntityModel.of(patient));
		mockMvc.perform(get(ROOT + "/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("patient/get"));
	}

	@Test
	void getPatientReturns404WhenPatientNotFound() throws Exception {
		doThrow(PatientNotFoundException.class).when(patientClient).getPatient(anyInt());
		mockMvc.perform(get(ROOT + "/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("error/404"));
	}

	@Test
	void getPatientReturns400WhenHttpClientException() {
		
	}

	@Test
	void showAddFormReturnsAdd() {
		
	}

	@Test
	void addPatientReturnsListWhenOk() {
		
	}

	@Test
	void addPatientReturnsListWhenPatientAlreadyExists() {
		
	}

	@Test
	void addPatientReturnsListWhenBadRequest() {
		
	}

	@Test
	void showUpdateFormReturnsUpdateWhenOk() {
		
	}

	@Test
	void showUpdateFormReturns404WhenPatientNotFound() {
		
	}

	@Test
	void updatePatientReturns200WhenOk() {
		
	}

	@Test
	void updatePatientReturnsListWhenPatientNotFound() {
		
	}

	@Test
	void updatePatientReturnsListWhenBadRequest() {
		
	}

}
