package com.tipikae.patientservice;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.patientservice.dto.NewPatientDTO;
import com.tipikae.patientservice.dto.PatientDTO;
import com.tipikae.patientservice.dto.UpdatePatientDTO;
import com.tipikae.patientservice.exception.PatientAlreadyExistsException;
import com.tipikae.patientservice.exception.PatientNotFoundException;
import com.tipikae.patientservice.service.IPatientServiceService;

@SpringBootTest
class PatientServiceIT {
	
	@Autowired
	private IPatientServiceService patientService;

	@Test
	void test() throws PatientAlreadyExistsException, PatientNotFoundException {
		long id;
		String family = "family";
		String given = "given";
		LocalDate dob = LocalDate.of(2000, 01, 01);
		char sex = 'M';
		String address = "address";
		String phone = "phone";
		
		// save
		NewPatientDTO newPatientDTO = new NewPatientDTO(family, given, dob, sex, address, phone);
		PatientDTO patientDTO = patientService.addPatient(newPatientDTO);
		id = patientDTO.getId();
		assertThrows(PatientAlreadyExistsException.class, () -> patientService.addPatient(newPatientDTO));
		
		// get by id
		assertEquals(given, patientService.getPatientById(id).getGiven());
		assertThrows(PatientNotFoundException.class, () -> patientService.getPatientById(100000L));
		
		// get by family
		assertTrue(patientService.getPatientsByFamily(family).size() == 1);
		assertTrue(patientService.getPatientsByFamily("anyFamily").isEmpty());
		
		// get all
		assertTrue(patientService.getAllPatients().size() > 0);
		
		// update patient
		String updatedAddress = "updatedeAddress";
		UpdatePatientDTO updatePatientDTO = new UpdatePatientDTO(dob, sex, updatedAddress, phone);
		patientService.updatePatient(id, updatePatientDTO);
		assertEquals(updatedAddress, patientService.getPatientById(id).getAddress());
		
		// delete patient
		patientService.deletePatient(id);
		assertThrows(PatientNotFoundException.class, () -> patientService.getPatientById(id));
	}

}
