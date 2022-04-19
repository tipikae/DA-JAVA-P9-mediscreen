/**
 * 
 */
package com.tipikae.patientservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.google.common.base.Optional;
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
	 * @return Optional
	 */
	Optional<Patient> findByFamilyAndGiven(String family, String given);
}
