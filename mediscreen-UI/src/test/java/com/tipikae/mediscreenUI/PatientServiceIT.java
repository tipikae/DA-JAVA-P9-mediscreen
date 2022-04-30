package com.tipikae.mediscreenUI;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.mediscreenUI.client.IPatientServiceClient;
import com.tipikae.mediscreenUI.dto.NewPatientDTO;
import com.tipikae.mediscreenUI.dto.UpdatePatientDTO;
import com.tipikae.mediscreenUI.exception.BadRequestException;
import com.tipikae.mediscreenUI.exception.HttpClientException;
import com.tipikae.mediscreenUI.exception.PatientAlreadyExistException;
import com.tipikae.mediscreenUI.exception.PatientNotFoundException;
import com.tipikae.mediscreenUI.model.Patient;

@SpringBootTest
class PatientServiceIT {
	
	@Autowired
	private IPatientServiceClient patientClient;
	
	@Test
	void test() 
			throws PatientAlreadyExistException, BadRequestException, HttpClientException, PatientNotFoundException {
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
		Patient patient = patientClient.addPatient(newPatientDTO);
		assertTrue(patient.getFamily().equals(family));
		assertThrows(PatientAlreadyExistException.class, () -> patientClient.addPatient(newPatientDTO));
		
		NewPatientDTO newPatientDTO2 = new NewPatientDTO("", given, dob, sex, address, phone);
		assertThrows(BadRequestException.class, () -> patientClient.addPatient(newPatientDTO2));
		
		// get all
		assertTrue(patientClient.getPatients(0, 5).getContent().size() > 0);
		
		// get one
		id = patient.getId();
		assertTrue(patientClient.getPatient(id).getGiven().equals(given));
		assertThrows(PatientNotFoundException.class, () -> patientClient.getPatient(10000));
		
		// update
		String updatedAddress = "updatedAddress";
		UpdatePatientDTO updatePatientDTO = new UpdatePatientDTO(dob, sex, updatedAddress, phone);
		patientClient.updatePatient(id, updatePatientDTO);
		assertTrue(patientClient.getPatient(id).getAddress().equals(updatedAddress));
		assertThrows(PatientNotFoundException.class, () -> patientClient.updatePatient(10000, updatePatientDTO));
		
		UpdatePatientDTO updatePatientDTO2 = 
				new UpdatePatientDTO(LocalDate.of(2000, 01, 01), 'F', "address", "");
		assertThrows(BadRequestException.class, () -> patientClient.updatePatient(10000, updatePatientDTO2));
		
		// delete
		assertThrows(PatientNotFoundException.class, () -> patientClient.deletePatient(10000));
		patientClient.deletePatient(id);
		assertThrows(PatientNotFoundException.class, () -> patientClient.deletePatient(id));
	}

}
