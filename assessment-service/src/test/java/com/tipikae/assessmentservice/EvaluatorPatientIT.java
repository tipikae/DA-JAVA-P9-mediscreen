package com.tipikae.assessmentservice;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.assessmentservice.exception.ExpressionValidationException;
import com.tipikae.assessmentservice.exception.ValidatorNotFoundException;
import com.tipikae.assessmentservice.model.Note;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.risk.core.IEvaluator;

@SpringBootTest
class EvaluatorPatientIT {
	
	@Autowired
	private IEvaluator evaluator;
	
	private static Patient patient;
	
	@BeforeAll
	private static void setUp() {
		patient = new Patient(1L, "family", "given", LocalDate.of(2000, 01, 01), 'M', "address", "phone");
	}
	
	@Test
	void evaluateExpressionReturnsTrueWhenTrue() 
			throws ExpressionValidationException, ValidatorNotFoundException {
		assertTrue(evaluator.evaluateExpression(patient, "P.age < 30"));
		assertTrue(evaluator.evaluateExpression(patient, "P.sex = M"));
	}
	
	@Test
	void evaluateExpressionReturnsFalseWhenFalse() 
			throws ExpressionValidationException, ValidatorNotFoundException {
		assertFalse(evaluator.evaluateExpression(patient, "P.age > 30"));
		assertFalse(evaluator.evaluateExpression(patient, "P.sex = F"));
	}
	
	@Test
	void evaluateExpressionThrowsExpressionValidationExceptionWhenExpressionError() {
		assertThrows(ExpressionValidationException.class, 
				() -> evaluator.evaluateExpression(patient, "M.age > 30"));
		assertThrows(ExpressionValidationException.class, 
				() -> evaluator.evaluateExpression(patient, "P.age & 30"));
		assertThrows(ExpressionValidationException.class, 
				() -> evaluator.evaluateExpression(patient, "P.field > 30"));
	}
	
	@Test
	void evaluateExpressionThrowsValidatorNotFoundExceptionWhenBadModel() {
		Note note = new Note("id", 1L, LocalDate.now(), "message");
		assertThrows(ValidatorNotFoundException.class, 
				() -> evaluator.evaluateExpression(note, "N.age > 30"));
	}

}
