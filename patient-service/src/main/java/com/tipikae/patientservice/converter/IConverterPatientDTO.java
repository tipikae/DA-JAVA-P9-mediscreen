/**
 * 
 */
package com.tipikae.patientservice.converter;

import java.util.List;

import org.springframework.data.domain.Page;

import com.tipikae.patientservice.dto.NewPatientDTO;
import com.tipikae.patientservice.dto.PatientDTO;
import com.tipikae.patientservice.dto.UpdatePatientDTO;
import com.tipikae.patientservice.model.Patient;

/**
 * Converter Patient-DTO.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IConverterPatientDTO {

	/**
	 * Convert a NewPatientDTO to a Patient.
	 * @param newPatientDTO NewPatientDTO
	 * @return Patient
	 */
	Patient convertNewPatientDTOToPatient(NewPatientDTO newPatientDTO);
	
	/**
	 * Convert UpdatePatientDTO.
	 * @param updatePatientDTO UpdatePatientDTO
	 * @param patient Patient
	 * @return Patient
	 */
	Patient convertUpdatePatientDTOToPatient(UpdatePatientDTO updatePatientDTO, Patient patient);
	
	/**
	 * Convert a Patient to PatientDTO.
	 * @param patient Patient
	 * @return PatientDTO
	 */
	PatientDTO convertPatientToDTO(Patient patient);
	
	/**
	 * Convert a Patient list to a DTO list.
	 * @param patients List
	 * @return List
	 */
	List<PatientDTO> convertPatientsToDTOs(List<Patient> patients);
	
	/**
	 * Convert a Patient page to a PatientDTO page.
	 * @param patients Page
	 * @return Page
	 */
	Page<PatientDTO> convertPagePatientsToPagePatientDTOs(Page<Patient> patients);
}
