/**
 * 
 */
package com.tipikae.assessmentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tipikae.assessmentservice.model.Risk;

/**
 * RiskRepository.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IRiskRepository extends JpaRepository<Risk, Long> {

}
