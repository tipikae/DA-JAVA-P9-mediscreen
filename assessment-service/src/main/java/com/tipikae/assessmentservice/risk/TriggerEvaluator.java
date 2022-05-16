/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tipikae.assessmentservice.client.INoteServiceClient;
import com.tipikae.assessmentservice.exception.BadOperationException;
import com.tipikae.assessmentservice.exception.ClientException;
import com.tipikae.assessmentservice.exception.NotFoundException;
import com.tipikae.assessmentservice.model.Note;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.service.TriggerTermsCounter;

/**
 * Trigger operation evaluator.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class TriggerEvaluator implements IEvaluator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TriggerEvaluator.class);
	
	@Autowired
	private OperationParser operationParser;
	
	@Autowired
	private INoteServiceClient noteClient;
	
	@Autowired
	private TriggerTermsCounter termsCounter;
	
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
			throws NotFoundException, BadOperationException, ClientException {
		LOGGER.debug("evaluate method: patientId=" + patient.getId() + ", operation=" + operation);
		List<String> elements = operationParser.getMethodElements(operation);
		
		if (!elements.isEmpty() && elements.size() == 3) {
			String method = elements.get(0);
			String operator = elements.get(1);
			String expected = elements.get(2);
			LOGGER.debug("method=" + method + ", operator=" + operator + ", expected=" + expected);
			
			List<Note> notes;
			try {
				notes = noteClient.getPatientNotes(patient.getId());
			} catch (Exception e) {
				LOGGER.debug("evaluate: exception=" + e.getClass().getSimpleName() + ": " + e.getMessage());
				throw new ClientException(e.getClass().getSimpleName() + ": " + e.getMessage());
			}
			
			int count = termsCounter.countTerms(notes);
			
			if(ArithmeticOperator.valueOfOperator(operator) != null) {
				LOGGER.debug("evaluate: operator=" + operator + ", count=" + count 
						+ ", expected=" + expected);
				return ArithmeticOperator.valueOfOperator(operator).apply(count, Integer.valueOf(expected));
			}
			
			LOGGER.debug("evaluate: Arithmetic operator=" + operator + " not found.");
			throw new NotFoundException("Arithmetic operator=" + operator + " not found.");
		}
		
		LOGGER.debug("Operation incorrect: operation=" + operation);
		throw new BadOperationException("Operation incorrect: operation=" + operation);
	}

}
