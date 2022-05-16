/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tipikae.assessmentservice.exception.BadOperationException;
import com.tipikae.assessmentservice.exception.NotFoundException;
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
	
	private Patient patient;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean evaluate(String operation) 
			throws NotFoundException, BadOperationException {
		LOGGER.debug("evaluate patient: patientId=" + patient.getId() + ", operation=" + operation);
		OperationParser operationParser = new OperationParser();
		List<String> elements = operationParser.getElements(operation);
		
		if (!elements.isEmpty() && elements.size() == 3) {
			String field = elements.get(0);
			String operator = elements.get(1);
			String expected = elements.get(2);
			LOGGER.debug("field=" + field + ", operator=" + operator + ", expected=" + expected);
			
			String getterName = "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
			Object obj;
			try {
				Class<? extends Patient> clazz = patient.getClass();
				Method getter = clazz.getMethod(getterName, (Class<?>[]) null);
				obj = getter.invoke(patient);
			} catch (Exception e) {
				LOGGER.debug("evaluate: invoke getter impossible: error=" + e.getMessage());
				throw new BadOperationException("invoke getter impossible: error=" + e.getMessage());
			}
			
			if(obj instanceof Integer) {
				if(ArithmeticOperator.valueOfOperator(operator) != null) {
					return ArithmeticOperator.valueOfOperator(operator)
							.apply(patient.getAge(), Integer.valueOf(expected));
				}
				
				LOGGER.debug("evaluate: Arithmetic operator=" + operator + " not found.");
				throw new NotFoundException("Arithmetic operator=" + operator + " not found.");
				
			} else if(obj instanceof Character) {
				if(CharacterOperator.valueOfOperator(operator) != null) {
					return CharacterOperator.valueOfOperator(operator)
							.apply(patient.getSex(), expected.charAt(0));
				}
				
				LOGGER.debug("evaluate: Character operator=" + operator + " not found.");
				throw new NotFoundException("Character operator=" + operator + " not found.");
				
			}
		}
		
		LOGGER.debug("evaluate: Operation incorrect: operation=" + operation);
		throw new BadOperationException("Operation incorrect: operation=" + operation);
	}

}
