package com.tipikae.assessmentservice.unit.risk;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.tipikae.assessmentservice.exception.BadOperationException;
import com.tipikae.assessmentservice.exception.NotFoundException;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.risk.PatientEvaluator;

class PatientEvaluatorTest {
	
	private PatientEvaluator patientEvaluator = new PatientEvaluator();
	
	private static Patient maleLess30;
	private static Patient femaleLess30;
	private static Patient maleMore30;
	private static Patient femaleMore30;
	private static String ageLess30;
	private static String ageMore30;
	private static String sexEqualMale;
	private static String sexEqualFemale;
	private static String prefix;
	private static String age;
	private static String sex;
	private static String thirty;
	private static String male;
	private static String female;
	private static String less;
	private static String more;
	private static String equals;
	
	@BeforeAll
	private static void setUp() {
		maleLess30 = new Patient(0, "", "", LocalDate.of(2000, 01, 01), 'M', "", "");
		maleMore30 = new Patient(0, "", "", LocalDate.of(1980, 01, 01), 'M', "", "");
		femaleLess30 = new Patient(0, "", "", LocalDate.of(2000, 01, 01), 'F', "", "");
		femaleMore30 = new Patient(0, "", "", LocalDate.of(1980, 01, 01), 'F', "", "");
		prefix = "P.";
		age = "age";
		sex = "sex";
		thirty = "30";
		male = "M";
		female = "F";
		less = "<";
		more = ">";
		equals = "=";
		ageLess30 = prefix + age + " " + less + " " + thirty;
		ageMore30 = prefix + age + " " + more + " " + thirty;
		sexEqualMale = prefix + sex + " " + equals + " " + male;
		sexEqualFemale = prefix + sex + " " + equals + " " + female;
	}

	@Test
	void evaluateReturnsTrueWhenOperationIsTrue() 
			throws NotFoundException, BadOperationException {
		patientEvaluator.setPatient(maleLess30);
		assertTrue(patientEvaluator.evaluate(ageLess30));
		patientEvaluator.setPatient(maleLess30);
		assertTrue(patientEvaluator.evaluate(sexEqualMale));
		patientEvaluator.setPatient(maleMore30);
		assertTrue(patientEvaluator.evaluate(ageMore30));
		patientEvaluator.setPatient(maleMore30);
		assertTrue(patientEvaluator.evaluate(sexEqualMale));


		patientEvaluator.setPatient(femaleLess30);
		assertTrue(patientEvaluator.evaluate(ageLess30));
		patientEvaluator.setPatient(femaleLess30);
		assertTrue(patientEvaluator.evaluate(sexEqualFemale));
		patientEvaluator.setPatient(femaleMore30);
		assertTrue(patientEvaluator.evaluate(ageMore30));
		patientEvaluator.setPatient(femaleMore30);
		assertTrue(patientEvaluator.evaluate(sexEqualFemale));
	}

	@Test
	void evaluateReturnsFalseWhenOperationIsFalse() 
			throws NotFoundException, BadOperationException {
		patientEvaluator.setPatient(maleLess30);
		assertFalse(patientEvaluator.evaluate(ageMore30));
		patientEvaluator.setPatient(maleLess30);
		assertFalse(patientEvaluator.evaluate(sexEqualFemale));
		patientEvaluator.setPatient(maleMore30);
		assertFalse(patientEvaluator.evaluate(ageLess30));
		patientEvaluator.setPatient(maleLess30);
		assertFalse(patientEvaluator.evaluate(sexEqualFemale));

		patientEvaluator.setPatient(femaleLess30);
		assertFalse(patientEvaluator.evaluate(ageMore30));
		patientEvaluator.setPatient(femaleLess30);
		assertFalse(patientEvaluator.evaluate(sexEqualMale));
		patientEvaluator.setPatient(femaleMore30);
		assertFalse(patientEvaluator.evaluate(ageLess30));
		patientEvaluator.setPatient(femaleMore30);
		assertFalse(patientEvaluator.evaluate(sexEqualMale));
	}
	
	@Test
	void evaluateThrowsBadOperationExceptionWhenBadOperator() {
		String operation = "P.age + 30";
		patientEvaluator.setPatient(femaleMore30);
		assertThrows(BadOperationException.class, 
				() -> patientEvaluator.evaluate(operation));
	}
	
	@Test
	void evaluateThrowsFieldNotFoundExceptionWhenBadField() {
		String operation = "P.city = city";
		patientEvaluator.setPatient(femaleLess30);
		assertThrows(NotFoundException.class, 
				() -> patientEvaluator.evaluate(operation));
	}
	
	@Test
	void evaluateThrowsBadOperationExceptionWhenEmptyOperation() {
		String operation = "";
		patientEvaluator.setPatient(femaleLess30);
		assertThrows(BadOperationException.class, 
				() -> patientEvaluator.evaluate(operation));
	}
	
	@Test
	void evaluateThrowsBadOperationExceptionWhenBadOperation() {
		String operation = "P.age =";
		patientEvaluator.setPatient(femaleLess30);
		assertThrows(BadOperationException.class, 
				() -> patientEvaluator.evaluate(operation));
	}

}
