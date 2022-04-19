package com.tipikae.patientservice.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tipikae.patientservice.converter.IConverterPatientDTO;
import com.tipikae.patientservice.dto.NewPatientDTO;
import com.tipikae.patientservice.dto.PatientDTO;
import com.tipikae.patientservice.dto.UpdatePatientDTO;
import com.tipikae.patientservice.exception.PatientAlreadyExistsException;
import com.tipikae.patientservice.exception.PatientNotFoundException;
import com.tipikae.patientservice.model.Patient;
import com.tipikae.patientservice.repository.IPatientRepository;
import com.tipikae.patientservice.service.PatientServiceServiceImpl;

@ExtendWith(MockitoExtension.class)
class PatientServiceServiceTest {
	
	@Mock
	private IConverterPatientDTO converterPatientDTO;
	
	@Mock
	private IPatientRepository patientRepository;
	
	@InjectMocks
	private PatientServiceServiceImpl patientService;
	
	private static long id;
	private static String family;
	private static String given;
	private static LocalDate dob;
	private static char sex;
	private static String address;
	private static String updatedAddress;
	private static String phone;
	private static Patient patient;
	private static PatientDTO patientDTO;
	private static NewPatientDTO newPatientDTO;
	private static UpdatePatientDTO updatePatientDTO;
	
	@BeforeAll
	private static void setUp() {
		id = 1;
		family = "family";
		given = "given";
		dob = LocalDate.of(2000, 01, 01);
		sex = 'F';
		address = "address";
		updatedAddress = "updatedAddress";
		phone = "pĥone";
		patient = new Patient(id, family, given, dob, sex, address, phone);
		patientDTO = new PatientDTO(id, family, given, dob, sex, address, phone);
		newPatientDTO = new NewPatientDTO(family, given, dob, sex, address, phone);
		updatePatientDTO = new UpdatePatientDTO(dob, sex, updatedAddress, phone);
	}

	@Test
	void addPatientReturnsPatientWhenOk() throws PatientAlreadyExistsException {
		when(patientRepository.findByFamilyAndGiven(anyString(), anyString())).thenReturn(new ArrayList<>());
		when(converterPatientDTO.convertNewPatientDTOToPatient(any(NewPatientDTO.class))).thenReturn(patient);
		when(patientRepository.save(any(Patient.class))).thenReturn(patient);
		when(converterPatientDTO.convertPatientToDTO(any(Patient.class))).thenReturn(patientDTO);
		assertEquals(family, patientService.addPatient(newPatientDTO).getFamily());
	}

	@Test
	void addPatientThorwsExceptionWhenAlreadyExists() {
		when(patientRepository.findByFamilyAndGiven(anyString(), anyString())).thenReturn(Arrays.asList(patient));
		assertThrows(PatientAlreadyExistsException.class, () -> patientService.addPatient(newPatientDTO));
	}

	@Test
	void getPatientByIdReturnsPatientWhenOk() throws PatientNotFoundException {
		when(patientRepository.findById(anyLong())).thenReturn(Optional.of(patient));
		when(converterPatientDTO.convertPatientToDTO(any(Patient.class))).thenReturn(patientDTO);
		assertEquals(family, patientService.getPatientById(id).getFamily());
	}

	@Test
	void getPatientByIdThrowsExceptionWhenNotFound() {
		when(patientRepository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(PatientNotFoundException.class, () -> patientService.getPatientById(10));
	}

	@Test
	void getAllPatients() {
		when(patientRepository.findAll()).thenReturn(Arrays.asList(patient));
		when(converterPatientDTO.convertPatientsToDTOs(anyList())).thenReturn(Arrays.asList(patientDTO));
		assertEquals(1, patientService.getAllPatients().size());
	}

	@Test
	void updatePatientCallsSaveWhenOk() throws PatientNotFoundException {
		when(patientRepository.findById(anyLong())).thenReturn(Optional.of(patient));
		when(converterPatientDTO.convertUpdatePatientDTOToPatient(any(UpdatePatientDTO.class), any(Patient.class)))
			.thenReturn(patient);
		patientService.updatePatient(id, updatePatientDTO);
		verify(patientRepository).save(any(Patient.class));
	}

	@Test
	void updatePatientThrowsExceptionWhenNotFound() {
		when(patientRepository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(PatientNotFoundException.class, () -> patientService.updatePatient(id, updatePatientDTO));
	}

	@Test
	void deletePatientCallsDeleteWhenOk() throws PatientNotFoundException {
		when(patientRepository.findById(anyLong())).thenReturn(Optional.of(patient));
		patientService.deletePatient(id);
		verify(patientRepository).deleteById(id);;
	}

	@Test
	void deletePatientThrowsExceptionWhenNotFound() {
		when(patientRepository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(PatientNotFoundException.class, () -> patientService.deletePatient(id));
	}

}
