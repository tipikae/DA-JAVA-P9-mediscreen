/**
 * 
 */
package com.tipikae.patientservice.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tipikae.patientservice.converter.IConverterPatientDTO;
import com.tipikae.patientservice.dto.NewPatientDTO;
import com.tipikae.patientservice.dto.PatientDTO;
import com.tipikae.patientservice.dto.UpdatePatientDTO;
import com.tipikae.patientservice.exception.PatientAlreadyExistsException;
import com.tipikae.patientservice.exception.PatientNotFoundException;
import com.tipikae.patientservice.model.Patient;
import com.tipikae.patientservice.repository.IPatientRepository;

/**
 * Patient service.
 * @author tipikae
 * @version
 *
 */
@Service
public class PatientServiceImpl implements IPatientService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PatientServiceImpl.class);
	
	@Autowired
	private IPatientRepository patientRepository;
	
	@Autowired
	private IConverterPatientDTO converterPatientDTO;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PatientDTO addPatient(NewPatientDTO newPatientDTO) throws PatientAlreadyExistsException {
		LOGGER.debug("addPatient: family=" + newPatientDTO.getFamily() + ", given=" + newPatientDTO.getGiven());
		List<Patient> patients = patientRepository.findByFamilyAndGiven(
				newPatientDTO.getFamily(), newPatientDTO.getGiven());
		if(!patients.isEmpty()) {
			LOGGER.debug("addPatient: Patient with family=" + newPatientDTO.getFamily() 
				+ ", given=" + newPatientDTO.getGiven() + " already exists.");
			throw new PatientAlreadyExistsException("Patient with family=" + newPatientDTO.getFamily() 
				+ ", given=" + newPatientDTO.getGiven() + " already exists.");
		}
		
		return converterPatientDTO.convertPatientToDTO(
				patientRepository.save(converterPatientDTO.convertNewPatientDTOToPatient(newPatientDTO)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PatientDTO getPatientById(long id) throws PatientNotFoundException {
		LOGGER.debug("getPatientById: id=" + id);
		Optional<Patient> optional = patientRepository.findById(id);
		if(optional.isEmpty()) {
			LOGGER.debug("getPatientById: patient with id=" + id + " not found.");
			throw new PatientNotFoundException("Patient with id=" + id + " not found.");
		}
		
		return converterPatientDTO.convertPatientToDTO(optional.get());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PatientDTO> getAllPatients() {
		LOGGER.debug("getAllPatients");
		return converterPatientDTO.convertPatientsToDTOs(patientRepository.findAll());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updatePatient(long id, UpdatePatientDTO updatePatientDTO) throws PatientNotFoundException {
		LOGGER.debug("updatePatient: id=" + id);
		Optional<Patient> optional = patientRepository.findById(id);
		if(optional.isEmpty()) {
			LOGGER.debug("updatePatient: patient with id=" + id + " not found.");
			throw new PatientNotFoundException("Patient with id=" + id + " not found.");
		}

		patientRepository.save(
				converterPatientDTO.convertUpdatePatientDTOToPatient(updatePatientDTO, optional.get()));
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deletePatient(long id) throws PatientNotFoundException {
		LOGGER.debug("deletePatient: id=" + id);
		Optional<Patient> optional = patientRepository.findById(id);
		if(optional.isEmpty()) {
			LOGGER.debug("deletePatient: patient with id=" + id + " not found.");
			throw new PatientNotFoundException("Patient with id=" + id + " not found.");
		}
		
		patientRepository.deleteById(id);
	}

}
