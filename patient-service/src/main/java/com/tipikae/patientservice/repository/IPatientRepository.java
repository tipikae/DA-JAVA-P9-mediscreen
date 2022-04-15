/**
 * 
 */
package com.tipikae.patientservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.tipikae.patientservice.model.Patient;

/**
 * Patient repository.
 * @author tipikae
 * @version 1.0
 *
 */
@RepositoryRestResource(collectionResourceRel = "patients", path = "patients")
public interface IPatientRepository extends JpaRepository<Patient, Long> {

	/**
	 * Find patients by family name.
	 * @param family String
	 * @return List
	 */
	List<Patient> findByFamily(@Param("family") String family);
}
