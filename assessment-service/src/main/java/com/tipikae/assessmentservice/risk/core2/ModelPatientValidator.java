/**
 * 
 */
package com.tipikae.assessmentservice.risk.core2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Validator for a Patient object.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class ModelPatientValidator implements IModelValidator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ModelPatientValidator.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean valid(String field, String operand, String expected) {
		LOGGER.debug("valid: field=" + field + ", operand=" + operand + ", expected=" + expected);
		// TODO Auto-generated method stub
		return false;
	}

}
