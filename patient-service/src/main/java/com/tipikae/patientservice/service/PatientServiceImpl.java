/**
 * 
 */
package com.tipikae.patientservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tipikae.patientservice.converter.IConverterPatientDTO;
import com.tipikae.patientservice.dto.NewPatientDTO;
import com.tipikae.patientservice.dto.PatientDTO;
import com.tipikae.patientservice.dto.UpdatePatientDTO;
import com.tipikae.patientservice.exception.PatientAlreadyExists;
import com.tipikae.patientservice.exception.PatientNotFoundException;
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
	public PatientDTO addPatient(NewPatientDTO newPatientDTO) throws PatientAlreadyExists {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PatientDTO getPatientById(int id) throws PatientNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<PatientDTO> getAllPatients() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updatePatient(int id, UpdatePatientDTO updatePatientDTO) throws PatientNotFoundException {
		// TODO Auto-generated method stub

	}

}
