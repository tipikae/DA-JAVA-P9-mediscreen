/**
 * 
 */
package com.tipikae.assessmentservice.service;

import java.time.LocalDate;
import java.time.Period;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Age provider.
 * @author tipikae
 * @version 1.0
 *
 */
@Service
public class AgeProvider {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AgeProvider.class);

	/**
	 * Calculate an age according to a date.
	 * @param dob LocalDate
	 * @return int
	 */
	public int calculateAge(LocalDate dob) {
		int age = Period.between(dob, LocalDate.now()).getYears();
		LOGGER.debug("calculateAge: dob=" + dob.toString() + ", age=" + age);
		
		return age;
	}

}
