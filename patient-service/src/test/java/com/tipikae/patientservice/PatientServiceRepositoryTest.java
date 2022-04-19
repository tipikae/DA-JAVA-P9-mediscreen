package com.tipikae.patientservice;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.patientservice.model.Patient;
import com.tipikae.patientservice.repository.IPatientRepository;

@SpringBootTest
class PatientServiceRepositoryTest {
	
	@Autowired
	private IPatientRepository patientRepository;

	@Test
	void test() {
		// save
		String family = "TestFamily";
		String given = "TestGiven";
		LocalDate dob = LocalDate.of(2000, 01, 01);
		char sex = 'M';
		String address = "testAddress";
		String phone = "testPhone";
		Patient patient = new Patient(0, family, given, dob, sex, address, phone);
		Patient patient2 = patientRepository.save(patient);
		
		assertTrue(patient2 != null);
		assertTrue(patientRepository.findAll().size() > 0);
		assertTrue(patientRepository.findById(patient2.getId()).isPresent());
		assertEquals(family, patientRepository.findById(patient2.getId()).get().getFamily());
		
		// update
		String newAddress = "newAddress";
		patient2.setAddress(newAddress);
		patientRepository.save(patient2);
		assertEquals(newAddress, patientRepository.findById(patient2.getId()).get().getAddress());
		
		// delete
		patientRepository.delete(patient2);
		assertFalse(patientRepository.findById(patient2.getId()).isPresent());
	}

}
