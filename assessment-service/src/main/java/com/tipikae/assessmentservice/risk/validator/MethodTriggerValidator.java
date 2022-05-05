/**
 * 
 */
package com.tipikae.assessmentservice.risk.validator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tipikae.assessmentservice.assessment.ITermCounter;
import com.tipikae.assessmentservice.assessment.TermCounterImpl;
import com.tipikae.assessmentservice.client.INoteServiceClient;
import com.tipikae.assessmentservice.exception.ExpressionValidationException;
import com.tipikae.assessmentservice.exception.OperandNotFoundException;
import com.tipikae.assessmentservice.model.Note;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.risk.comparator.ComparatorImpl;
import com.tipikae.assessmentservice.risk.comparator.IComparator;

/**
 * Method trigger validator.
 * @author tipikae
 * @version 1.0
 *
 */
public class MethodTriggerValidator implements IMethodValidator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodTriggerValidator.class);
	
	@Autowired
	private INoteServiceClient noteClient;
	
	private ITermCounter termCounter = new TermCounterImpl();
	private IComparator comparator = new ComparatorImpl();
	private int count;
	
	/**
	 * Count trigger terms and set count field.
	 * @param object Object
	 * @throws ExpressionValidationException
	 */
	public void trigger(Object object) throws ExpressionValidationException {
		LOGGER.debug("trigger");
		if(!(object instanceof Patient)) {
			LOGGER.debug("trigger: object with class=" + object.getClass().getSimpleName() 
					+ " is not a Patient");
			throw new ExpressionValidationException("Bad object class=" 
					+ object.getClass().getSimpleName() + " instead of Patient");
		}
		
		Patient patient = (Patient) object;
		try {
			List<Note> notes = noteClient.getPatientNotes(patient.getId());
			count = termCounter.countTerms(notes);
		} catch (Exception e) {
			LOGGER.debug("trigger: exception=" + e.getClass().getSimpleName() + ", " + e.getMessage());
			throw new ExpressionValidationException(e.getMessage());
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean valid(String operand, String value) throws ExpressionValidationException {
		LOGGER.debug("valid: operand=" + operand + ", value=" + value);
		try {
			return comparator.compareInt(operand, count, Integer.parseInt(value));
		} catch (NumberFormatException | OperandNotFoundException e) {
			LOGGER.debug("valid: exception=" + e.getClass().getSimpleName() + ", " + e.getMessage());
			throw new ExpressionValidationException(e.getMessage());
		}
	}

}
