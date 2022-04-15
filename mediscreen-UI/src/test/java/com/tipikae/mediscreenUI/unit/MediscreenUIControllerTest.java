package com.tipikae.mediscreenUI.unit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import com.tipikae.mediscreenUI.dto.NewPatientDTO;
import com.tipikae.mediscreenUI.dto.UpdatePatientDTO;
import com.tipikae.mediscreenUI.exception.BadRequestException;
import com.tipikae.mediscreenUI.exception.HttpClientException;
import com.tipikae.mediscreenUI.exception.PatientAlreadyExistException;
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
		mockMvc.perform(get(ROOT + "/all"))
			.andExpect(status().isOk())
			.andExpect(view().name("patient/list"));
	}

	@Test
	void getAllPatientsReturns400WhenBadRequest() throws Exception {
		doThrow(BadRequestException.class).when(patientClient).getPatients();
		mockMvc.perform(get(ROOT + "/all"))
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
	void getPatientReturns400WhenHttpClientException() throws Exception {
		doThrow(HttpClientException.class).when(patientClient).getPatient(anyInt());
		mockMvc.perform(get(ROOT + "/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("error/400"));
	}

	@Test
	void showAddFormReturnsAdd() throws Exception {
		mockMvc.perform(get(ROOT + "/add"))
			.andExpect(status().isOk())
			.andExpect(view().name("patient/add"));
	}

	@Test
	void addPatientReturnsListWhenOk() throws Exception {
		NewPatientDTO newPatientDTO = new NewPatientDTO("family", "given", LocalDate.now(), 'F', "address", "phone");
		when(patientClient.addPatient(any(NewPatientDTO.class))).thenReturn(EntityModel.of(new Patient()));
		mockMvc.perform(post(ROOT + "/add")
				.flashAttr("patient", newPatientDTO))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/list?success=New patient added."));
	}

	@Test
	void addPatientReturnsAddWhenBadParameter() throws Exception {
		NewPatientDTO newPatientDTO = new NewPatientDTO("family", "", LocalDate.now(), 'F', "address", "phone");
		mockMvc.perform(post(ROOT + "/add")
				.flashAttr("patient", newPatientDTO))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:patient/add?error=Firstname must not be empty. "));
	}

	@Test
	void addPatientReturnsListWhenPatientAlreadyExists() throws Exception {
		NewPatientDTO newPatientDTO = new NewPatientDTO("family", "given", LocalDate.now(), 'F', "address", "phone");
		doThrow(PatientAlreadyExistException.class).when(patientClient).addPatient(any(NewPatientDTO.class));
		mockMvc.perform(post(ROOT + "/add")
				.flashAttr("patient", newPatientDTO))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/list?error=Patient already exists."));
	}

	@Test
	void addPatientReturnsListWhenBadRequest() throws Exception {
		NewPatientDTO newPatientDTO = new NewPatientDTO("family", "given", LocalDate.now(), 'F', "address", "phone");
		doThrow(BadRequestException.class).when(patientClient).addPatient(any(NewPatientDTO.class));
		mockMvc.perform(post(ROOT + "/add")
				.flashAttr("patient", newPatientDTO))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/list?error=Request error."));
	}

	@Test
	void showUpdateFormReturnsUpdateWhenOk() throws Exception {
		when(patientClient.getPatient(anyInt())).thenReturn(EntityModel.of(new Patient()));
		mockMvc.perform(get(ROOT + "/update/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("patient/update"));
	}

	@Test
	void showUpdateFormReturns404WhenPatientNotFound() throws Exception {
		doThrow(PatientNotFoundException.class).when(patientClient).getPatient(anyInt());
		mockMvc.perform(get(ROOT + "/update/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("error/404"));
	}

	@Test
	void updatePatientReturns200WhenOk() throws Exception {
		UpdatePatientDTO updatePatientDTO = 
				new UpdatePatientDTO("family", "given", LocalDate.now(), 'F', "address", "phone");
		doNothing().when(patientClient).updatePatient(anyInt(), any(UpdatePatientDTO.class));
		mockMvc.perform(put(ROOT + "/update/1")
				.flashAttr("patient", updatePatientDTO))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/list?success=Patient updated."));
	}

	@Test
	void updatePatientReturnsErrorWhenBadParameter() throws Exception {
		int id = 1;
		UpdatePatientDTO updatePatientDTO = 
				new UpdatePatientDTO("", "given", LocalDate.now(), 'F', "address", "phone");
		mockMvc.perform(put(ROOT + "/update/" + id)
				.flashAttr("patient", updatePatientDTO))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/update/" + id + "?error=" + "Lastname must not be empty. "));
	}

	@Test
	void updatePatientReturnsListWhenPatientNotFound() throws Exception {
		UpdatePatientDTO updatePatientDTO = 
				new UpdatePatientDTO("family", "given", LocalDate.now(), 'F', "address", "phone");
		doThrow(PatientNotFoundException.class)
			.when(patientClient).updatePatient(anyInt(), any(UpdatePatientDTO.class));
		mockMvc.perform(put(ROOT + "/update/1")
				.flashAttr("patient", updatePatientDTO))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/list?error=Patient not found."));
	}

	@Test
	void updatePatientReturnsListWhenBadRequest() throws Exception {
		UpdatePatientDTO updatePatientDTO = 
				new UpdatePatientDTO("family", "given", LocalDate.now(), 'F', "address", "phone");
		doThrow(BadRequestException.class).when(patientClient).updatePatient(anyInt(), any(UpdatePatientDTO.class));
		mockMvc.perform(put(ROOT + "/update/1")
				.flashAttr("patient", updatePatientDTO))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/list?error=Request error."));
	}

}
