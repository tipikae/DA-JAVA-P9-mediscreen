package com.tipikae.assessmentservice;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.assessmentservice.exception.ExpressionValidationException;
import com.tipikae.assessmentservice.exception.ValidatorNotFoundException;
import com.tipikae.assessmentservice.model.Note;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.risk.IEvaluator;
import com.tipikae.assessmentservice.risk.ModelEvaluator;

@SpringBootTest
class EvaluatorPatientIT {
	
	private static Patient patient;
	
	@BeforeAll
	private static void setUp() {
		patient = new Patient(1L, "family", "given", LocalDate.of(2000, 01, 01), 'M', "address", "phone");
	}
	
	@Test
	void evaluateExpressionReturnsTrueWhenTrue() 
			throws ExpressionValidationException, ValidatorNotFoundException {
		IEvaluator evaluator = new ModelEvaluator(patient);
		assertTrue(evaluator.evaluateExpression("P.age < 30"));
		assertTrue(evaluator.evaluateExpression("P.sex = M"));
	}
	
	@Test
	void evaluateExpressionReturnsFalseWhenFalse() 
			throws ExpressionValidationException, ValidatorNotFoundException {
		IEvaluator evaluator = new ModelEvaluator(patient);
		assertFalse(evaluator.evaluateExpression("P.age > 30"));
		assertFalse(evaluator.evaluateExpression("P.sex = F"));
	}
	
	@Test
	void evaluateExpressionThrowsExpressionValidationExceptionWhenExpressionError() {
		IEvaluator evaluator = new ModelEvaluator(patient);
		assertThrows(ExpressionValidationException.class, () -> evaluator.evaluateExpression("M.age > 30"));
		assertThrows(ExpressionValidationException.class, () -> evaluator.evaluateExpression("P.age & 30"));
		assertThrows(ExpressionValidationException.class, () -> evaluator.evaluateExpression("P.field > 30"));
	}
	
	@Test
	void evaluateExpressionThrowsValidatorNotFoundExceptionWhenBadModel() {
		Note note = new Note("id", 1L, LocalDate.now(), "message");
		IEvaluator evaluator = new ModelEvaluator(note);
		assertThrows(ValidatorNotFoundException.class, () -> evaluator.evaluateExpression("N.age > 30"));
	}

}
