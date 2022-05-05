/**
 * 
 */
package com.tipikae.assessmentservice.risk.validator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tipikae.assessmentservice.exception.ExpressionValidationException;
import com.tipikae.assessmentservice.exception.OperandNotFoundException;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.risk.comparator.IComparator;
import com.tipikae.assessmentservice.risk.parser.IExpressionParser;
import com.tipikae.assessmentservice.util.IUtil;

/**
 * Validator for a Patient object.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class ModelPatientValidator implements IModelValidator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ModelPatientValidator.class);
	private static final char PREFIX = 'P';
	private static final String AGE = "age";
	private static final String SEX = "sex";
	
	@Autowired
	private IExpressionParser expressionParser;
	
	@Autowired
	private IComparator comparator;
	
	@Autowired
	private IUtil util;
	
	private Patient patient;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setObject(Object obj) {
		LOGGER.debug("setObject");
		patient = (Patient) obj;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean valid(String expression) throws ExpressionValidationException {
		LOGGER.debug("valid: patientId=" + patient.getId() + ", expression=" + expression);
		List<String> elements = expressionParser.getModelElements(PREFIX, expression);
		
		if (!elements.isEmpty() && elements.size() == 3) {
			String field = elements.get(0);
			String operand = elements.get(1);
			String value = elements.get(2);
			
			if (field.equals(AGE)) {
				int age = util.calculateAge(patient.getDob());
				try {
					return comparator.compareInt(operand, age, Integer.parseInt(value));
				} catch (NumberFormatException | OperandNotFoundException e) {
					LOGGER.debug("valid: " + e.getClass().getSimpleName() 
							+ " occured when comparator called");
					throw new ExpressionValidationException(e.getMessage());
				}
			} else if (field.equals(SEX)) {
				try {
					return comparator.compareCharacter(operand, patient.getSex(), value.charAt(0));
				} catch (OperandNotFoundException e) {
					LOGGER.debug("valid: " + e.getClass().getSimpleName() 
							+ " occured when comparator called");
					throw new ExpressionValidationException(e.getMessage());
				}
			}
		}
		
		LOGGER.debug("Expression incorrect: expression=" + expression);
		throw new ExpressionValidationException("Expression incorrect: expression=" + expression);
	}

}
