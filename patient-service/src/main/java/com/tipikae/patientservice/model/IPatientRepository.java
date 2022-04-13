/**
 * 
 */
package com.tipikae.patientservice.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Patient repository.
 * @author tipikae
 * @version 1.0
 *
 */
@RepositoryRestResource
public interface IPatientRepository extends JpaRepository<Patient, Integer> {

}
