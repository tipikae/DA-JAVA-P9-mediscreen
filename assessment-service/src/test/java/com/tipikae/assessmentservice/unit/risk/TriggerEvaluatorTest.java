package com.tipikae.assessmentservice.unit.risk;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tipikae.assessmentservice.client.INoteServiceClient;
import com.tipikae.assessmentservice.exception.BadOperationException;
import com.tipikae.assessmentservice.exception.BadRequestException;
import com.tipikae.assessmentservice.exception.ClientException;
import com.tipikae.assessmentservice.exception.HttpClientException;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.risk.TriggerEvaluator;
import com.tipikae.assessmentservice.service.TriggerTermsCounter;

@ExtendWith(MockitoExtension.class)
class TriggerEvaluatorTest {
	
	@Mock
	private INoteServiceClient noteClient;
	
	@Mock
	private TriggerTermsCounter termsCounter;
	
	@InjectMocks
	private TriggerEvaluator triggerEvaluator;
	
	private static Patient patient;
	private static String rightMethod;
	private static String rightOperator;
	private static String badOperator;
	private static String expected;
	private static String rightOperation;
	private static String badOperatorOperation;
	private static String badOperation;
	
	@BeforeAll
	private static void setUp() {
		patient = new Patient(1L, "", "", null, 'F', "", "");
		
		rightMethod = "trigger";
		rightOperator = "=";
		expected = "2";
		rightOperation = rightMethod + " " + rightOperator + " " + expected;
		
		badOperator = "?";
		badOperatorOperation = rightMethod + " " + badOperator + " " + expected;
		
		badOperation = rightMethod + " " + rightOperator;
	}

	@Test
	void evaluateReturnsTrueWhenOperationIsTrue() throws Exception {
		when(noteClient.getPatientNotes(anyLong())).thenReturn(List.of());
		when(termsCounter.countTerms(anyList())).thenReturn(Integer.valueOf(expected));
		triggerEvaluator.setPatient(patient);
		assertTrue(triggerEvaluator.evaluate(rightOperation));
	}

	@Test
	void evaluateReturnsFalseWhenOperationIsFalse() throws Exception {
		when(noteClient.getPatientNotes(anyLong())).thenReturn(List.of());
		when(termsCounter.countTerms(anyList())).thenReturn(Integer.valueOf(expected) + 1);
		triggerEvaluator.setPatient(patient);
		assertFalse(triggerEvaluator.evaluate(rightOperation));
	}
	
	@Test
	void evaluateThrowsClientExceptionWhenNoteClientError() 
			throws BadRequestException, HttpClientException {
		doThrow(HttpClientException.class).when(noteClient).getPatientNotes(anyLong());
		triggerEvaluator.setPatient(patient);
		assertThrows(ClientException.class, () -> triggerEvaluator.evaluate(rightOperation));
	}
	
	@Test
	void evaluateThrowsBadOperationExceptionWhenBadOperator() 
			throws BadRequestException, HttpClientException {
		triggerEvaluator.setPatient(patient);
		assertThrows(BadOperationException.class, 
				() -> triggerEvaluator.evaluate(badOperatorOperation));
	}
	
	@Test
	void evaluateThrowsBadOperationExceptionWhenBadOperation() 
			throws BadRequestException, HttpClientException {
		triggerEvaluator.setPatient(patient);
		assertThrows(BadOperationException.class, 
				() -> triggerEvaluator.evaluate(badOperation));
	}

}
