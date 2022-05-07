/**
 * 
 */
package com.tipikae.assessmentservice.risk2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.risk.parser.IExpressionParser;
import com.tipikae.assessmentservice.service.AgeProvider;

/**
 * Patient operation evaluator.
 * @author tipikae
 * @version 1.0
 *
 */
public class PatientEvaluator implements IEvaluator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PatientEvaluator.class);
	private static final char PREFIX = 'P';
	private static final String AGE = "age";
	private static final String SEX = "sex";
	
	@Autowired
	private AgeProvider ageProvider;
	
	@Autowired
	private IExpressionParser expressionParser;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean evaluate(Patient patient, String operation) {
		// TODO Auto-generated method stub
		return false;
	}

}
