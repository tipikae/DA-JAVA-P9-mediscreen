package com.tipikae.mediscreenUI.unit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

import com.tipikae.mediscreenUI.client.INoteServiceClient;
import com.tipikae.mediscreenUI.client.IPatientServiceClient;
import com.tipikae.mediscreenUI.controller.PatientController;
import com.tipikae.mediscreenUI.dto.NewPatientDTO;
import com.tipikae.mediscreenUI.dto.UpdatePatientDTO;
import com.tipikae.mediscreenUI.exception.BadRequestException;
import com.tipikae.mediscreenUI.exception.HttpClientException;
import com.tipikae.mediscreenUI.exception.PatientAlreadyExistException;
import com.tipikae.mediscreenUI.exception.PatientNotFoundException;
import com.tipikae.mediscreenUI.model.Patient;

@WebMvcTest(controllers = PatientController.class)
class PatientControllerTest {
	
	private static final String ROOT = "/patient"; 
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IPatientServiceClient patientClient;
	
	@MockBean
	private INoteServiceClient noteClient;

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Get all patients
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void getAllPatientsReturns200ListWhenOk() throws Exception {
		Patient patient = new Patient(1L, "family", "given", LocalDate.of(2000, 01, 01), 'F', "address", "phone");
		when(patientClient.getPatients(anyInt(), anyInt())).thenReturn(new PageImpl<>(List.of(patient)));
		mockMvc.perform(get(ROOT + "/all"))
			.andExpect(status().isOk())
			.andExpect(view().name("patient/list"));
	}

	@Test
	void getAllPatientsReturns400WhenBadRequest() throws Exception {
		doThrow(BadRequestException.class).when(patientClient).getPatients(anyInt(), anyInt());
		mockMvc.perform(get(ROOT + "/all"))
			.andExpect(status().isOk())
			.andExpect(view().name("error/400"));
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Get a patient
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void getPatientReturns200PatientWhenOk() throws Exception {
		Patient patient = new Patient(1L, "family", "given", LocalDate.of(2000, 01, 01), 'F', "address", "phone");
		when(patientClient.getPatient(anyLong())).thenReturn(patient);
		mockMvc.perform(get(ROOT + "/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("patient/get"));
	}

	@Test
	void getPatientReturns404WhenPatientNotFound() throws Exception {
		doThrow(PatientNotFoundException.class).when(patientClient).getPatient(anyLong());
		mockMvc.perform(get(ROOT + "/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("error/404"));
	}

	@Test
	void getPatientReturns400WhenHttpClientException() throws Exception {
		doThrow(HttpClientException.class).when(patientClient).getPatient(anyLong());
		mockMvc.perform(get(ROOT + "/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("error/400"));
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Show add form
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void showAddFormReturns200Add() throws Exception {
		mockMvc.perform(get(ROOT + "/add"))
			.andExpect(status().isOk())
			.andExpect(view().name("patient/add"));
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Add a patient
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void addPatientReturns3xxSuccessWhenOk() throws Exception {
		NewPatientDTO newPatientDTO = 
				new NewPatientDTO("family", "given", LocalDate.of(2000, 01, 01), 'F', "address", "phone");
		when(patientClient.addPatient(any(NewPatientDTO.class))).thenReturn(new Patient());
		mockMvc.perform(post(ROOT + "/add")
				.flashAttr("patient", newPatientDTO))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/all?success=New patient added."));
	}

	@Test
	void addPatientReturns200WhenBadField() throws Exception {
		NewPatientDTO newPatientDTO = 
				new NewPatientDTO("family", "", LocalDate.of(2000, 01, 01), 'F', "address", "phone");
		mockMvc.perform(post(ROOT + "/add")
				.flashAttr("patient", newPatientDTO))
			.andExpect(status().isOk())
			.andExpect(view().name("patient/add"));
	}

	@Test
	void addPatientReturns3xxErrorWhenPatientAlreadyExists() throws Exception {
		NewPatientDTO newPatientDTO = 
				new NewPatientDTO("family", "given", LocalDate.of(2000, 01, 01), 'F', "address", "phone");
		doThrow(PatientAlreadyExistException.class).when(patientClient).addPatient(any(NewPatientDTO.class));
		mockMvc.perform(post(ROOT + "/add")
				.flashAttr("patient", newPatientDTO))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/all?error=Patient already exists."));
	}

	@Test
	void addPatientReturns3xxErrorWhenBadRequest() throws Exception {
		NewPatientDTO newPatientDTO = 
				new NewPatientDTO("family", "given", LocalDate.of(2000, 01, 01), 'F', "address", "phone");
		doThrow(BadRequestException.class).when(patientClient).addPatient(any(NewPatientDTO.class));
		mockMvc.perform(post(ROOT + "/add")
				.flashAttr("patient", newPatientDTO))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/all?error=Request error."));
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Show update form
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void showUpdateFormReturns200UpdateWhenOk() throws Exception {
		when(patientClient.getPatient(anyLong()))
			.thenReturn(new Patient(1, "family", "given", LocalDate.of(2000, 01, 01), 'F', "address", "phone"));
		mockMvc.perform(get(ROOT + "/update/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("patient/update"));
	}

	@Test
	void showUpdateFormReturns404WhenPatientNotFound() throws Exception {
		doThrow(PatientNotFoundException.class).when(patientClient).getPatient(anyLong());
		mockMvc.perform(get(ROOT + "/update/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("error/404"));
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Update a patient
	//////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void updatePatientReturns3xxSuccessWhenOk() throws Exception {
		int id = 1;
		UpdatePatientDTO updatePatientDTO = 
				new UpdatePatientDTO(LocalDate.of(2000, 01, 01), 'F', "address", "phone");
		doNothing().when(patientClient).updatePatient(anyLong(), any(UpdatePatientDTO.class));
		mockMvc.perform(post(ROOT + "/update/" + id)
				.flashAttr("patient", updatePatientDTO))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/" + id + "?success=Patient updated."));
	}

	@Test
	void updatePatientReturns3xxErrorWhenBadField() throws Exception {
		long id = 1;
		UpdatePatientDTO updatePatientDTO = 
				new UpdatePatientDTO(LocalDate.of(2000, 01, 01), 'F', "", "phone");
		mockMvc.perform(post(ROOT + "/update/" + id)
				.flashAttr("patient", updatePatientDTO))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/update/" + id + "?error=" + "Address must not be empty. "));
	}

	@Test
	void updatePatientReturns3xxErrorWhenPatientNotFound() throws Exception {
		UpdatePatientDTO updatePatientDTO = 
				new UpdatePatientDTO(LocalDate.of(2000, 01, 01), 'F', "address", "phone");
		doThrow(PatientNotFoundException.class)
			.when(patientClient).updatePatient(anyLong(), any(UpdatePatientDTO.class));
		mockMvc.perform(post(ROOT + "/update/1")
				.flashAttr("patient", updatePatientDTO))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/all?error=Patient not found."));
	}

	@Test
	void updatePatientReturns3xxErrorWhenBadRequest() throws Exception {
		int id = 1;
		UpdatePatientDTO updatePatientDTO = 
				new UpdatePatientDTO(LocalDate.of(2000, 01, 01), 'F', "address", "phone");
		doThrow(BadRequestException.class).when(patientClient).updatePatient(anyLong(), any(UpdatePatientDTO.class));
		mockMvc.perform(post(ROOT + "/update/" + id)
				.flashAttr("patient", updatePatientDTO))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/all?error=Request error."));
	}

}
