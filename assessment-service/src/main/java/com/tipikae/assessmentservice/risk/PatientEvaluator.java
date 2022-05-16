/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tipikae.assessmentservice.exception.BadOperationException2;
import com.tipikae.assessmentservice.exception.FieldNotFoundException2;
import com.tipikae.assessmentservice.exception.OperatorNotFoundException2;
import com.tipikae.assessmentservice.model.Patient;

/**
 * Patient operation evaluator.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class PatientEvaluator implements IEvaluator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PatientEvaluator.class);
	private static final char PREFIX = 'P';
	private static final String AGE = "age";
	private static final String SEX = "sex";
	
	@Autowired
	private OperationParser operationParser;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean evaluate(Patient patient, String operation) 
			throws OperatorNotFoundException2, FieldNotFoundException2, BadOperationException2 {
		LOGGER.debug("evaluate patient: patientId=" + patient.getId() + ", operation=" + operation);
		List<String> elements = operationParser.getModelElements(PREFIX, operation);
		
		if (!elements.isEmpty() && elements.size() == 3) {
			String field = elements.get(0);
			String operator = elements.get(1);
			String expected = elements.get(2);
			LOGGER.debug("field=" + field + ", operator=" + operator + ", expected=" + expected);
			
			if (field.equals(AGE)) {
				if(ArithmeticOperator.valueOfOperator(operator) != null) {
					LOGGER.debug("evaluate: operator=" + operator + ", age=" + patient.getAge() 
							+ ", expected=" + expected);
					return ArithmeticOperator.valueOfOperator(operator)
							.apply(patient.getAge(), Integer.valueOf(expected));
				}
				
				LOGGER.debug("evaluate: Arithmetic operator=" + operator + " not found.");
				throw new OperatorNotFoundException2("Arithmetic operator=" + operator + " not found.");
			
			} else if (field.equals(SEX)) {
				if(CharacterOperator.valueOfOperator(operator) != null) {
					LOGGER.debug("evaluate: operator=" + operator + ", sex=" + patient.getSex() 
							+ ", expected=" + expected);
					return CharacterOperator.valueOfOperator(operator)
							.apply(patient.getSex(), expected.charAt(0));
				}
				
				LOGGER.debug("evaluate: Character operator=" + operator + " not found.");
				throw new OperatorNotFoundException2("Character operator=" + operator + " not found.");
			
			} else {
				LOGGER.debug("evaluate: Patient field=" + field + " not found.");
				throw new FieldNotFoundException2("Patient field=" + field + " not found.");
			}
		}
		
		LOGGER.debug("Operation incorrect: operation=" + operation);
		throw new BadOperationException2("Operation incorrect: operation=" + operation);
	}

}
