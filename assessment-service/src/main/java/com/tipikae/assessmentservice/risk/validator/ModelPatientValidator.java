/**
 * 
 */
package com.tipikae.assessmentservice.risk.validator;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tipikae.assessmentservice.exception.ExpressionValidationException;
import com.tipikae.assessmentservice.exception.OperandNotFoundException;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.risk.comparator.ComparatorImpl;
import com.tipikae.assessmentservice.risk.comparator.IComparator;
import com.tipikae.assessmentservice.risk.parser.ExpressionParserImpl;
import com.tipikae.assessmentservice.risk.parser.IExpressionParser;
import com.tipikae.assessmentservice.util.IUtil;
import com.tipikae.assessmentservice.util.UtilImpl;

/**
 * Model Patient validator.
 * @author tipikae
 * @version 1.0
 *
 */
public class ModelPatientValidator implements IModelValidator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ModelPatientValidator.class);
	private static final char PREFIX = 'P';
	private static final String AGE = "age";
	private static final String SEX = "sex";

	private IExpressionParser expressionParser = new ExpressionParserImpl();
	private IComparator comparator = new ComparatorImpl();
	private IUtil util = new UtilImpl();
	
	private Patient patient;
	
	public ModelPatientValidator(Patient patient) {
		this.patient = patient;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean valid(String expression) 
			throws ExpressionValidationException {
		LOGGER.debug("valid patientId=" + patient.getId() + ", expression=" + expression);
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
					throw new ExpressionValidationException(e.getMessage());
				}
			} else if (field.equals(SEX)) {
				try {
					return comparator.compareCharacter(operand, patient.getSex(), value.charAt(0));
				} catch (OperandNotFoundException e) {
					throw new ExpressionValidationException(e.getMessage());
				}
			} 
		}
		
		throw new ExpressionValidationException("Expression incorrect: expression=" + expression);
	}

}
