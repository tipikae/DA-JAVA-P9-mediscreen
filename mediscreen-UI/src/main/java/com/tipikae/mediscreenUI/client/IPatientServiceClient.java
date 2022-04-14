/**
 * 
 */
package com.tipikae.mediscreenUI.client;

import java.util.List;

import com.tipikae.mediscreenUI.dto.NewPatientDTO;
import com.tipikae.mediscreenUI.dto.UpdatePatientDTO;
import com.tipikae.mediscreenUI.model.Patient;

/**
 * Feign client for Patient Service.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IPatientServiceClient {

	
	List<Patient> getPatients();
	Patient getPatient(int id);
	Patient addPatient(NewPatientDTO newPatientDTO);
	void updatePatient(int id, UpdatePatientDTO updatePatientDTO);
}
