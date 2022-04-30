/**
 * 
 */
package com.tipikae.patientservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tipikae.patientservice.model.Patient;

/**
 * Patient repository.
 * @author tipikae
 * @version 1.0
 *
 */
@Repository
public interface IPatientRepository extends JpaRepository<Patient, Long> {

	/**
	 * Find a patient by its family name AND its given name.
	 * @param family String
	 * @param given String
	 * @return List
	 */
	List<Patient> findByFamilyAndGiven(String family, String given);

	/**
	 * Find patients by family name.
	 * @param family String
	 * @return List
	 */
	List<Patient> findByFamily(String family);
}
