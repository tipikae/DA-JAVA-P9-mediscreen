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

@SpringBootTest
class MediscreenIT {
	
	@Autowired
	private IPatientServiceClient patientClient;

	@Test
	void getPatientsReturnsListWhenOk() throws BadRequestException, HttpClientException {
		assertTrue(patientClient.getPatients().getContent().size() > 0);
	}

	@Test
	void getPatientReturnsPatientWhenOk() throws PatientNotFoundException, BadRequestException, HttpClientException {
		assertTrue(patientClient.getPatient(1).getContent().getGiven().equals("Test"));
	}

	@Test
	void getPatientReturnsNotFoundWhenNotFound() {
		assertThrows(PatientNotFoundException.class, () -> patientClient.getPatient(10000));
	}

	@Test
	void addPatientReturnsPatientWhenOk() 
			throws PatientAlreadyExistException, BadRequestException, HttpClientException {
		String lastname = "TestUI";
		NewPatientDTO newPatientDTO = new NewPatientDTO(lastname, "UI", LocalDate.now(), 'M', "address", "phone");
		assertTrue(patientClient.addPatient(newPatientDTO).getContent().getFamily().equals(lastname));
	}

	@Test
	void addPatientReturnsAlreadyExistsWhenAlreadyExists() 
			throws PatientAlreadyExistException, BadRequestException, HttpClientException {
		NewPatientDTO newPatientDTO = new NewPatientDTO("TestUI2", "UI", LocalDate.now(), 'M', "address", "phone");
		patientClient.addPatient(newPatientDTO);
		assertThrows(PatientAlreadyExistException.class, () -> patientClient.addPatient(newPatientDTO));
	}

	@Test
	void addPatientReturnsBadRequestWhenInvalidParameter() {
		NewPatientDTO newPatientDTO = new NewPatientDTO("", "UI", LocalDate.now(), 'M', "address", "phone");
		assertThrows(BadRequestException.class, () -> patientClient.addPatient(newPatientDTO));
	}

	@Test
	void updatePatientWhenOk() 
			throws PatientAlreadyExistException, BadRequestException, HttpClientException, PatientNotFoundException {
		String family = "TestUI3";
		String given = "UI3";
		LocalDate date = LocalDate.now();
		char sex = 'M';
		String address = "address";
		String phone = "phone";
		String updatedAddress = "updatedAddress";
		NewPatientDTO newPatientDTO = new NewPatientDTO(family, given, date, sex, address, phone);
		int id = (int) patientClient.addPatient(newPatientDTO).getContent().getId();
		UpdatePatientDTO updatePatientDTO = new UpdatePatientDTO(family, given, date, sex, updatedAddress, phone);
		patientClient.updatePatient(id, updatePatientDTO);
		assertTrue(patientClient.getPatient(id).getContent().getAddress().equals(updatedAddress));
	}

	/*@Test
	void updatePatientReturnsNotFoundWhenNotFound() {
		UpdatePatientDTO updatePatientDTO = 
				new UpdatePatientDTO("updateWhen", "notFound", LocalDate.now(), 'F', "address", "phone");
		assertThrows(PatientNotFoundException.class, () -> patientClient.updatePatient(10000, updatePatientDTO));
	}*/

	@Test
	void updatePatientReturnsBadRequestWhenInvalidParameter() {
		UpdatePatientDTO updatePatientDTO = 
				new UpdatePatientDTO("family", "", LocalDate.now(), 'F', "address", "phone");
		assertThrows(BadRequestException.class, () -> patientClient.updatePatient(10000, updatePatientDTO));
	}

}
