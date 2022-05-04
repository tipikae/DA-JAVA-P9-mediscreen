package com.tipikae.assessmentservice;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.assessmentservice.exception.ExpressionValidationException;
import com.tipikae.assessmentservice.exception.ValidatorNotFoundException;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.risk.core.IEvaluator;
import com.tipikae.assessmentservice.risk.core.MethodEvaluator;

@SpringBootTest
class EvaluatorTriggerIT {
	
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
		IEvaluator evaluator = new MethodEvaluator(patientNone);
		assertTrue(evaluator.evaluateExpression("trigger = 1"));
		evaluator = new MethodEvaluator(patientBorderline);
		assertTrue(evaluator.evaluateExpression("trigger = 2"));
		evaluator = new MethodEvaluator(patientInDanger);
		assertTrue(evaluator.evaluateExpression("trigger = 3"));
		evaluator = new MethodEvaluator(patientEarlyOnset);
		assertTrue(evaluator.evaluateExpression("trigger = 9"));
	}

	@Test
	void evaluateExpressionReturnsFalseWhenFalse() 
			throws ExpressionValidationException, ValidatorNotFoundException {
		IEvaluator evaluator = new MethodEvaluator(patientNone);
		assertFalse(evaluator.evaluateExpression("trigger = 0"));
		evaluator = new MethodEvaluator(patientBorderline);
		assertFalse(evaluator.evaluateExpression("trigger = 0"));
		evaluator = new MethodEvaluator(patientInDanger);
		assertFalse(evaluator.evaluateExpression("trigger = 0"));
		evaluator = new MethodEvaluator(patientEarlyOnset);
		assertFalse(evaluator.evaluateExpression("trigger = 0"));
	}

	@Test
	void evaluateExpressionThrowsExpressionValidationExceptionWhenExpressionError() {
		IEvaluator evaluator = new MethodEvaluator(patientNone);
		assertThrows(ExpressionValidationException.class, 
				() -> evaluator.evaluateExpression("trigger & 0"));
	}

	@Test
	void evaluateExpressionThrowsValidatorNotFoundExceptionWhenBadMethod() {
		IEvaluator evaluator = new MethodEvaluator(patientNone);
		assertThrows(ValidatorNotFoundException.class, 
				() -> evaluator.evaluateExpression("add = 0"));
	}

}
