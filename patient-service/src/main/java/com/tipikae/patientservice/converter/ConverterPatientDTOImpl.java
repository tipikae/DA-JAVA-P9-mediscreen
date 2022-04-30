/**
 * 
 */
package com.tipikae.patientservice.converter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.tipikae.patientservice.dto.NewPatientDTO;
import com.tipikae.patientservice.dto.PatientDTO;
import com.tipikae.patientservice.dto.UpdatePatientDTO;
import com.tipikae.patientservice.model.Patient;

/**
 * Convert Patient to DTO and vice-versa.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class ConverterPatientDTOImpl implements IConverterPatientDTO {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Patient convertNewPatientDTOToPatient(NewPatientDTO newPatientDTO) {
		Patient patient = new Patient();
		patient.setFamily(newPatientDTO.getFamily());
		patient.setGiven(newPatientDTO.getGiven());
		patient.setDob(newPatientDTO.getDob());
		patient.setSex(newPatientDTO.getSex());
		patient.setAddress(newPatientDTO.getAddress());
		patient.setPhone(newPatientDTO.getPhone());
		
		return patient;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Patient convertUpdatePatientDTOToPatient(UpdatePatientDTO updatePatientDTO, Patient patient) {
		patient.setDob(updatePatientDTO.getDob());
		patient.setSex(updatePatientDTO.getSex());
		patient.setAddress(updatePatientDTO.getAddress());
		patient.setPhone(updatePatientDTO.getPhone());
		
		return patient;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PatientDTO convertPatientToDTO(Patient patient) {
		PatientDTO patientDTO = new PatientDTO();
		patientDTO.setId(patient.getId());
		patientDTO.setFamily(patient.getFamily());
		patientDTO.setGiven(patient.getGiven());
		patientDTO.setDob(patient.getDob());
		patientDTO.setSex(patient.getSex());
		patientDTO.setAddress(patient.getAddress());
		patientDTO.setPhone(patient.getPhone());
		
		return patientDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PatientDTO> convertPatientsToDTOs(List<Patient> patients) {
		List<PatientDTO> patientDTOs = new ArrayList<>();
		patients.forEach(patient -> patientDTOs.add(convertPatientToDTO(patient)));
		
		return patientDTOs;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Page<PatientDTO> convertPagePatientsToPagePatientDTOs(Page<Patient> patients) {
		Page<PatientDTO> pageDTO = patients.map(patient -> {
			return convertPatientToDTO(patient);
		});
		return pageDTO;
	}

	

}
