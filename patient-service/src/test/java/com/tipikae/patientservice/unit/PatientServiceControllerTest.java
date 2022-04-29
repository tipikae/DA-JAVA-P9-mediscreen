package com.tipikae.patientservice.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.tipikae.patientservice.controller.PatientServiceController;
import com.tipikae.patientservice.dto.NewPatientDTO;
import com.tipikae.patientservice.dto.PatientDTO;
import com.tipikae.patientservice.dto.UpdatePatientDTO;
import com.tipikae.patientservice.exception.PatientAlreadyExistsException;
import com.tipikae.patientservice.exception.PatientNotFoundException;
import com.tipikae.patientservice.service.IPatientServiceService;

@WebMvcTest(controllers = PatientServiceController.class)
class PatientServiceControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IPatientServiceService patientService;
	
	private static final String ROOT = "/patients";
	
	private static long id;
	private static String family;
	private static String given;
	private static LocalDate dob;
	private static char sex;
	private static String address;
	private static String phone;
	private static PatientDTO patientDTO;
	
	@BeforeAll
	private static void setUp() {
		id = 1;
		family = "family";
		given = "given";
		dob = LocalDate.of(2000, 01, 01);
		sex = 'F';
		address = "address";
		phone = "phone";
		patientDTO = new PatientDTO(id, family, given, dob, sex, address, phone);
	}

	@Test
	void getAllPatientsReturns200ListWhenOk() throws Exception {
		when(patientService.getAllPatients()).thenReturn(Arrays.asList(patientDTO));
		mockMvc.perform(get(ROOT + "/"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.[0].family", is(family)));
	}

	@Test
	void getPatientByIdReturns200PatientWhenOk() throws Exception {
		when(patientService.getPatientById(anyLong())).thenReturn(patientDTO);
		mockMvc.perform(get(ROOT + "/id/" + id))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.given", is(given)));
	}

	@Test
	void getPatientByIdReturns404WhenNotFound() throws Exception {
		doThrow(PatientNotFoundException.class).when(patientService).getPatientById(anyLong());
		mockMvc.perform(get(ROOT + "/id/" + id))
			.andExpect(status().is(404));
	}

	@Test
	void getPatientByIdReturns400WhenBadIdParameter() throws Exception {
		mockMvc.perform(get(ROOT + "/id/0"))
			.andExpect(status().is(400));
	}

	@Test
	void getPatientsByFamilyReturns200ListWhenOk() throws Exception {
		when(patientService.getPatientsByFamily(anyString())).thenReturn(Arrays.asList(patientDTO));
		mockMvc.perform(get(ROOT + "/familyName/" + family))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.[0].family", is(family)));
	}

	@Test
	void addPatientReturns201PatientWhenOk() throws Exception {
		when(patientService.addPatient(any(NewPatientDTO.class))).thenReturn(patientDTO);
		mockMvc.perform(post(ROOT + "/")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"family\": \"family\", "
						+ "\"given\": \"given\", "
						+ "\"dob\": \"2000-01-01\", "
						+ "\"sex\": \"F\", "
						+ "\"address\": \"address\", "
						+ "\"phone\": \"phone\"}"))
			.andExpect(status().is(201))
			.andExpect(jsonPath("$.address", is(address)));
	}

	@Test
	void addPatientReturns400WhenBadDTOParameter() throws Exception {
		mockMvc.perform(post(ROOT + "/")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"family\": \"family\", "
						+ "\"dob\": \"2000-01-01\", "
						+ "\"sex\": \"F\", "
						+ "\"address\": \"address\", "
						+ "\"phone\": \"phone\"}"))
			.andExpect(status().is(400));
	}

	@Test
	void addPatientReturns409WhenAlreadyExists() throws Exception {
		doThrow(PatientAlreadyExistsException.class).when(patientService).addPatient(any(NewPatientDTO.class));
		mockMvc.perform(post(ROOT + "/")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"family\": \"family\", "
						+ "\"given\": \"given\", "
						+ "\"dob\": \"2000-01-01\", "
						+ "\"sex\": \"F\", "
						+ "\"address\": \"address\", "
						+ "\"phone\": \"phone\"}"))
			.andExpect(status().is(409));
	}

	@Test
	void updatePatientReturns200WhenOk() throws Exception {
		doNothing().when(patientService).updatePatient(anyLong(), any(UpdatePatientDTO.class));
		mockMvc.perform(put(ROOT + "/" + id)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"dob\": \"2000-01-01\", "
						+ "\"sex\": \"F\", "
						+ "\"address\": \"updatedAddress\", "
						+ "\"phone\": \"phone\"}"))
			.andExpect(status().is(200));
	}

	@Test
	void updatePatientReturns404WhenNotFound() throws Exception {
		doThrow(PatientNotFoundException.class)
			.when(patientService).updatePatient(anyLong(), any(UpdatePatientDTO.class));
		mockMvc.perform(put(ROOT + "/" + id)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"dob\": \"2000-01-01\", "
						+ "\"sex\": \"F\", "
						+ "\"address\": \"updatedAddress\", "
						+ "\"phone\": \"phone\"}"))
			.andExpect(status().is(404));
	}

	@Test
	void updatePatientReturns400WhenBadIdParameter() throws Exception {
		mockMvc.perform(put(ROOT + "/0")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"dob\": \"2000-01-01\", "
						+ "\"sex\": \"F\", "
						+ "\"address\": \"updatedAddress\", "
						+ "\"phone\": \"phone\"}"))
			.andExpect(status().is(400));
	}

	@Test
	void updatePatientReturns400WhenBadDTOParameter() throws Exception {
		mockMvc.perform(put(ROOT + "/0")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"dob\": \"2000-01-01\", "
						+ "\"sex\": \"F\", "
						+ "\"address\": \"updatedAddress\", "
						+ "\"phone\": \"\"}"))
			.andExpect(status().is(400));
	}

	@Test
	void deletePatientReturns200WhenOk() throws Exception {
		doNothing().when(patientService).deletePatient(anyLong());
		mockMvc.perform(delete(ROOT + "/" + id))
			.andExpect(status().is(200));
	}

	@Test
	void deletePatientReturns404WhenNotFound() throws Exception {
		doThrow(PatientNotFoundException.class)
			.when(patientService).deletePatient(anyLong());
		mockMvc.perform(delete(ROOT + "/" + id))
			.andExpect(status().is(404));
	}

	@Test
	void deletePatientReturns400WhenBadIdParameter() throws Exception {
		mockMvc.perform(delete(ROOT + "/0"))
			.andExpect(status().is(400));
	}

}
