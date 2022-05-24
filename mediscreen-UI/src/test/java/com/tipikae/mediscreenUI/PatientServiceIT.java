package com.tipikae.mediscreenUI;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.mediscreenUI.dto.NewPatientDTO;
import com.tipikae.mediscreenUI.dto.UpdatePatientDTO;
import com.tipikae.mediscreenUI.exception.BadRequestException;
import com.tipikae.mediscreenUI.exception.HttpClientException;
import com.tipikae.mediscreenUI.exception.AlreadyExistsException;
import com.tipikae.mediscreenUI.exception.NotFoundException;
import com.tipikae.mediscreenUI.model.Patient;
import com.tipikae.mediscreenUI.service.IPatientService;

@SpringBootTest
class PatientServiceIT {
	
	@Autowired
	private IPatientService patientService;
	
	@Test
	void test() 
			throws AlreadyExistsException, BadRequestException, HttpClientException, NotFoundException {
		long id;
		String family = "UIfamily";
		String given = "UIgiven";
		LocalDate dob = LocalDate.of(2000, 01, 01);
		char sex = 'F';
		String address = "address";
		String phone = "phone";
		
		// save
		NewPatientDTO newPatientDTO = 
				new NewPatientDTO(family, given, dob, sex, address, phone);
		Patient patient = patientService.addPatient(newPatientDTO);
		assertTrue(patient.getFamily().equals(family));
		assertThrows(AlreadyExistsException.class, () -> patientService.addPatient(newPatientDTO));
		
		NewPatientDTO newPatientDTO2 = new NewPatientDTO("", given, dob, sex, address, phone);
		assertThrows(BadRequestException.class, () -> patientService.addPatient(newPatientDTO2));
		
		// get all
		assertTrue(patientService.getPatients(0, 5).getContent().size() > 0);
		
		// get one
		id = patient.getId();
		assertTrue(patientService.getPatient(id).getGiven().equals(given));
		assertThrows(NotFoundException.class, () -> patientService.getPatient(10000));
		
		// update
		String updatedAddress = "updatedAddress";
		UpdatePatientDTO updatePatientDTO = new UpdatePatientDTO(dob, sex, updatedAddress, phone);
		patientService.updatePatient(id, updatePatientDTO);
		assertTrue(patientService.getPatient(id).getAddress().equals(updatedAddress));
		assertThrows(NotFoundException.class, () -> patientService.updatePatient(10000, updatePatientDTO));
		
		UpdatePatientDTO updatePatientDTO2 = 
				new UpdatePatientDTO(LocalDate.of(2000, 01, 01), 'F', "address", "");
		assertThrows(BadRequestException.class, () -> patientService.updatePatient(10000, updatePatientDTO2));
		
		// delete
		assertThrows(NotFoundException.class, () -> patientService.deletePatient(10000));
		patientService.deletePatient(id);
		assertThrows(NotFoundException.class, () -> patientService.deletePatient(id));
	}

}
