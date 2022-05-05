package com.tipikae.assessmentservice;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.assessmentservice.exception.ExpressionValidationException;
import com.tipikae.assessmentservice.exception.ValidatorNotFoundException;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.risk.core.IEvaluator;

@SpringBootTest
class EvaluatorTriggerIT {
	
	@Autowired
	private IEvaluator evaluator;
	
	private static Patient patientNone;
	private static Patient patientBorderline;
	private static Patient patientInDanger;
	private static Patient patientEarlyOnset;
	
	@BeforeAll
	private static void setUp() {
		patientNone = new Patient(1L, "", "", LocalDate.now(), 'M', "", "");
		patientBorderline = new Patient(2L, "", "", LocalDate.now(), 'M', "", "");
		patientInDanger = new Patient(3L, "", "", LocalDate.now(), 'M', "", "");
		patientEarlyOnset = new Patient(4L, "", "", LocalDate.now(), 'M', "", "");
	}

	@Test
	void evaluateExpressionReturnsTrueWhenTrue() 
			throws ExpressionValidationException, ValidatorNotFoundException {
		assertTrue(evaluator.evaluateExpression(patientNone, "trigger = 1"));
		assertTrue(evaluator.evaluateExpression(patientBorderline, "trigger = 2"));
		assertTrue(evaluator.evaluateExpression(patientInDanger, "trigger = 3"));
		assertTrue(evaluator.evaluateExpression(patientEarlyOnset, "trigger = 9"));
	}

	@Test
	void evaluateExpressionReturnsFalseWhenFalse() 
			throws ExpressionValidationException, ValidatorNotFoundException {
		assertFalse(evaluator.evaluateExpression(patientNone, "trigger = 0"));
		assertFalse(evaluator.evaluateExpression(patientBorderline, "trigger = 0"));
		assertFalse(evaluator.evaluateExpression(patientInDanger, "trigger = 0"));
		assertFalse(evaluator.evaluateExpression(patientEarlyOnset, "trigger = 0"));
	}

	@Test
	void evaluateExpressionThrowsExpressionValidationExceptionWhenExpressionError() {
		assertThrows(ExpressionValidationException.class, 
				() -> evaluator.evaluateExpression(patientNone, "trigger & 0"));
	}

	@Test
	void evaluateExpressionThrowsValidatorNotFoundExceptionWhenBadMethod() {
		assertThrows(ValidatorNotFoundException.class, 
				() -> evaluator.evaluateExpression(patientNone, "add = 0"));
	}

}
