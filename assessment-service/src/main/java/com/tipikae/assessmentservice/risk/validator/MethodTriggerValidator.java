/**
 * 
 */
package com.tipikae.assessmentservice.risk.validator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tipikae.assessmentservice.client.INoteServiceClient;
import com.tipikae.assessmentservice.exception.ExpressionValidationException;
import com.tipikae.assessmentservice.model.Note;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.risk.comparator.IComparator;
import com.tipikae.assessmentservice.risk.term.ITermCounter;

/**
 * Validator for trigger method.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class MethodTriggerValidator implements IMethodValidator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodTriggerValidator.class);
	
	@Autowired
	private INoteServiceClient noteClient;
	
	@Autowired
	private ITermCounter termCounter;
	
	@Autowired
	private IComparator comparator;
	
	private Patient patient;

	@Override
	public void setObject(Object obj) {
		LOGGER.debug("setObject");
		patient = (Patient) obj;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean valid(String operand, String expected) throws ExpressionValidationException {
		LOGGER.debug("valid: patientId=" + patient.getId() + ", operand=" + operand 
				+ ", expected=" + expected);
		try {
			List<Note> notes = noteClient.getPatientNotes(patient.getId());
			int count = termCounter.countTerms(notes);
			return comparator.compareInt(operand, count, Integer.parseInt(expected));
		} catch (Exception e) {
			LOGGER.debug("valid: error=" + e.getClass().getSimpleName() + ", " + e.getMessage());
			throw new ExpressionValidationException(e.getMessage());
		}
	}

}
