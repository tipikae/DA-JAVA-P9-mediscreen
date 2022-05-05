/**
 * 
 */
package com.tipikae.assessmentservice.util;

import java.time.LocalDate;
import java.time.Period;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Utilities.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class UtilImpl implements IUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int calculateAge(LocalDate dob) {
		int age = Period.between(dob, LocalDate.now()).getYears();
		LOGGER.debug("calculateAge: dob=" + dob.toString() + ", age=" + age);
		
		return age;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean matches(String expression, String regex) {
		LOGGER.debug("matches: expression=" + expression + ", regex=" + regex);
		if(expression.matches(regex)) {
			LOGGER.debug("matches: yes");
			return true;
		}
		
		LOGGER.debug("matches: no");
		return false;
	}

}
