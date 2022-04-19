/**
 * 
 */
package com.tipikae.patientservice.converter;

import java.util.List;

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
	 * @return Patient
	 */
	Patient convertUpdatePatientDTOToPatient(UpdatePatientDTO updatePatientDTO);
	
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
}
