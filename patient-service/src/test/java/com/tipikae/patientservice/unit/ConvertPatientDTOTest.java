package com.tipikae.patientservice.unit;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.tipikae.patientservice.dto.ConverterPatientDTOImpl;
import com.tipikae.patientservice.dto.IConverterPatientDTO;
import com.tipikae.patientservice.dto.NewPatientDTO;
import com.tipikae.patientservice.dto.PatientDTO;
import com.tipikae.patientservice.dto.UpdatePatientDTO;
import com.tipikae.patientservice.model.Patient;

class ConvertPatientDTOTest {
	
	private IConverterPatientDTO converter = new ConverterPatientDTOImpl();
	
	private String family = "family";
	private String given = "given";
	private LocalDate dob = LocalDate.of(2000, 01, 01);
	private char sex = 'M';
	private String address = "address";
	private String phone = "phone";

	@Test
	void convertNewPatientDTOToPatient() {
		NewPatientDTO newPatientDTO = new NewPatientDTO(family, given, dob, sex, address, phone);
		assertEquals(address, converter.convertNewPatientDTOToPatient(newPatientDTO).getAddress());
	}

	@Test
	void convertUpdatePatientDTOToPatient() {
		String newAddress = "newAddress";
		Patient patient = new Patient(1, family, given, dob, sex, address, phone);
		UpdatePatientDTO updatePatientDTO = new UpdatePatientDTO(dob, sex, newAddress, phone);
		assertEquals(newAddress, converter.convertUpdatePatientDTOToPatient(updatePatientDTO, patient).getAddress());
	}

	@Test
	void convertPatientToDTO() {
		int id = 1;
		Patient patient = new Patient(id, family, given, dob, sex, address, phone);
		assertEquals(id, converter.convertPatientToDTO(patient).getId());
	}

	@Test
	void convertPatientsToDTOs() {
		Patient patient = new Patient(1, family, given, dob, sex, address, phone);
		List<Patient> patients = Arrays.asList(patient);
		assertTrue(converter.convertPatientsToDTOs(patients).size() == 1);
	}
	
	@Test
	void convertPagePatientsToPagePatientDTOs() {
		Patient patient = new Patient(1, family, given, dob, sex, address, phone);
		Page<Patient> pages = new PageImpl<>(List.of(patient));
		Page<PatientDTO> pageDTOs = converter.convertPagePatientsToPagePatientDTOs(pages);
		assertEquals(1, pageDTOs.getNumberOfElements());
		assertEquals(phone, pageDTOs.getContent().get(0).getPhone());
	}

}
