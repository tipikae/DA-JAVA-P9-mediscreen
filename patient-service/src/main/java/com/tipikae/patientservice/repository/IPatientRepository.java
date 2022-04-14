/**
 * 
 */
package com.tipikae.patientservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
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

}
