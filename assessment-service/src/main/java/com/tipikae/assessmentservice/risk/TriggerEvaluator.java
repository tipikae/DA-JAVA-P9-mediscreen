/**
 * 
 */
package com.tipikae.assessmentservice.risk2;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tipikae.assessmentservice.client.INoteServiceClient;
import com.tipikae.assessmentservice.exception.BadOperationException2;
import com.tipikae.assessmentservice.exception.ClientException;
import com.tipikae.assessmentservice.exception.OperatorNotFoundException2;
import com.tipikae.assessmentservice.model.Note;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.service.TriggerTermsCounter;

/**
 * Trigger operation evaluator.
 * @author tipikae
 * @version 1.0
 *
 */
public class TriggerEvaluator implements IEvaluator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TriggerEvaluator.class);
	
	@Autowired
	private OperationParser operationParser;
	
	@Autowired
	private INoteServiceClient noteClient;
	
	@Autowired
	private TriggerTermsCounter termsCounter;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean evaluate(Patient patient, String operation) 
			throws OperatorNotFoundException2, BadOperationException2, ClientException {
		LOGGER.debug("evaluate method: patientId=" + patient.getId() + "operation=" + operation);
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
				return ArithmeticOperator.valueOfOperator(operator).apply(count, Integer.valueOf(expected));
			}
			
			LOGGER.debug("evaluate: Arithmetic operator=" + operator + " not found.");
			throw new OperatorNotFoundException2("Arithmetic operator=" + operator + " not found.");
		}
		
		LOGGER.debug("Operation incorrect: operation=" + operation);
		throw new BadOperationException2("Operation incorrect: operation=" + operation);
	}

}
