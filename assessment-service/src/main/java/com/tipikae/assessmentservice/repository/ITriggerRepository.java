/**
 * 
 */
package com.tipikae.assessmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tipikae.assessmentservice.model.Trigger;

/**
 * Trigger repository.
 * @author tipikae
 * @version 1.0
 *
 */
@Repository
public interface ITriggerRepository extends JpaRepository<Trigger, Long> {

}
