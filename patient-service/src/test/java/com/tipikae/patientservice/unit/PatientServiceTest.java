package com.tipikae.patientservice.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tipikae.patientservice.converter.IConverterPatientDTO;
import com.tipikae.patientservice.repository.IPatientRepository;
import com.tipikae.patientservice.service.PatientServiceImpl;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {
	
	@Mock
	private IConverterPatientDTO converterPatientDTO;
	
	@Mock
	private IPatientRepository patientRepository;
	
	@InjectMocks
	private PatientServiceImpl patientService;

	@Test
	void addPatient() {
		
	}

	@Test
	void getPatientById() {
		
	}

	@Test
	void getAllPatients() {
		
	}

	@Test
	void updatePatient() {
		
	}

}
