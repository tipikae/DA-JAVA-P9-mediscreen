/**
 * 
 */
package com.tipikae.assessmentservice.risk.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tipikae.assessmentservice.model.Patient;

/**
 * Method trigger validator.
 * @author tipikae
 * @version 1.0
 *
 */
public class MethodTriggerValidator implements IMethodValidator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodTriggerValidator.class);
	
	private int count;
	
	public void trigger(Object object) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean valid(String operand, String value) {
		// TODO Auto-generated method stub
		return false;
	}

}
