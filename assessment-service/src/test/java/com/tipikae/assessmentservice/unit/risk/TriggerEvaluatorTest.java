package com.tipikae.assessmentservice.unit.risk2;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
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
import com.tipikae.assessmentservice.exception.BadOperationException2;
import com.tipikae.assessmentservice.exception.BadRequestException;
import com.tipikae.assessmentservice.exception.ClientException;
import com.tipikae.assessmentservice.exception.HttpClientException;
import com.tipikae.assessmentservice.exception.OperatorNotFoundException2;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.risk2.OperationParser;
import com.tipikae.assessmentservice.risk2.TriggerEvaluator;
import com.tipikae.assessmentservice.service.TriggerTermsCounter;

@ExtendWith(MockitoExtension.class)
class TriggerEvaluatorTest {
	
	@Mock
	private OperationParser operationParser;
	
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
	private static List<String> rightParsed;
	private static List<String> badOperatorParsed;
	private static List<String> badOperationParsed;
	
	@BeforeAll
	private static void setUp() {
		patient = new Patient(1L, "", "", null, 'F', "", "");
		
		rightMethod = "trigger";
		rightOperator = "=";
		expected = "2";
		rightOperation = rightMethod + " " + rightOperator + " " + expected;
		rightParsed = List.of(rightMethod, rightOperator, expected);
		
		badOperator = "?";
		badOperatorOperation = rightMethod + " " + badOperator + " " + expected;
		badOperatorParsed = List.of(rightMethod, badOperator, expected);
		
		badOperation = rightMethod + " " + rightOperator;
		badOperationParsed = List.of(rightMethod, rightOperator);
	}

	@Test
	void evaluateReturnsTrueWhenOperationIsTrue() throws Exception {
		when(operationParser.getMethodElements(anyString())).thenReturn(rightParsed);
		when(noteClient.getPatientNotes(anyLong())).thenReturn(List.of());
		when(termsCounter.countTerms(anyList())).thenReturn(Integer.valueOf(expected));
		assertTrue(triggerEvaluator.evaluate(patient, rightOperation));
	}

	@Test
	void evaluateReturnsFalseWhenOperationIsFalse() throws Exception {
		when(operationParser.getMethodElements(anyString())).thenReturn(rightParsed);
		when(noteClient.getPatientNotes(anyLong())).thenReturn(List.of());
		when(termsCounter.countTerms(anyList())).thenReturn(Integer.valueOf(expected) + 1);
		assertFalse(triggerEvaluator.evaluate(patient, rightOperation));
	}
	
	@Test
	void evaluateThrowsClientExceptionWhenNoteClientError() 
			throws BadRequestException, HttpClientException {
		when(operationParser.getMethodElements(anyString())).thenReturn(rightParsed);
		doThrow(HttpClientException.class).when(noteClient).getPatientNotes(anyLong());
		assertThrows(ClientException.class, () -> triggerEvaluator.evaluate(patient, rightOperation));
	}
	
	@Test
	void evaluateThrowsOperatorNotFoundExceptionWhenBadOperator() 
			throws BadRequestException, HttpClientException {
		when(operationParser.getMethodElements(anyString())).thenReturn(badOperatorParsed);
		when(noteClient.getPatientNotes(anyLong())).thenReturn(List.of());
		when(termsCounter.countTerms(anyList())).thenReturn(Integer.valueOf(expected));
		assertThrows(OperatorNotFoundException2.class, 
				() -> triggerEvaluator.evaluate(patient, badOperatorOperation));
	}
	
	@Test
	void evaluateThrowsBadOperationExceptionWhenBadOperation() 
			throws BadRequestException, HttpClientException {
		when(operationParser.getMethodElements(anyString())).thenReturn(badOperationParsed);
		assertThrows(BadOperationException2.class, 
				() -> triggerEvaluator.evaluate(patient, badOperation));
	}

}
