package com.tipikae.assessmentservice.unit.risk;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tipikae.assessmentservice.exception.BadOperationException;
import com.tipikae.assessmentservice.exception.NotFoundException;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.risk.OperationParser;
import com.tipikae.assessmentservice.risk.PatientEvaluator;

@ExtendWith(MockitoExtension.class)
class PatientEvaluatorTest {
	
	@Mock
	private OperationParser operationParser;
	
	@InjectMocks
	private PatientEvaluator patientEvaluator;
	
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
	private static List<String> ageLess30Array;
	private static List<String> ageMore30Array;
	private static List<String> sexEqualMaleArray;
	private static List<String> sexEqualFemaleArray;
	
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
		ageLess30Array = Arrays.asList(age, less, thirty);
		ageMore30Array = Arrays.asList(age, more, thirty);
		sexEqualMaleArray = Arrays.asList(sex, equals, male);
		sexEqualFemaleArray = Arrays.asList(sex, equals, female);
	}

	@SuppressWarnings("unchecked")
	@Test
	void evaluateReturnsTrueWhenOperationIsTrue() 
			throws NotFoundException, BadOperationException {
		when(operationParser.getModelElements(anyChar(), anyString()))
			.thenReturn(ageLess30Array, sexEqualMaleArray, ageMore30Array, sexEqualMaleArray);
		patientEvaluator.setPatient(maleLess30);
		assertTrue(patientEvaluator.evaluate(ageLess30));
		patientEvaluator.setPatient(maleLess30);
		assertTrue(patientEvaluator.evaluate(sexEqualMale));
		patientEvaluator.setPatient(maleMore30);
		assertTrue(patientEvaluator.evaluate(ageMore30));
		patientEvaluator.setPatient(maleMore30);
		assertTrue(patientEvaluator.evaluate(sexEqualMale));


		when(operationParser.getModelElements(anyChar(), anyString()))
			.thenReturn(ageLess30Array, sexEqualFemaleArray, ageMore30Array, sexEqualFemaleArray);
		patientEvaluator.setPatient(femaleLess30);
		assertTrue(patientEvaluator.evaluate(ageLess30));
		patientEvaluator.setPatient(femaleLess30);
		assertTrue(patientEvaluator.evaluate(sexEqualFemale));
		patientEvaluator.setPatient(femaleMore30);
		assertTrue(patientEvaluator.evaluate(ageMore30));
		patientEvaluator.setPatient(femaleMore30);
		assertTrue(patientEvaluator.evaluate(sexEqualFemale));
	}

	@SuppressWarnings("unchecked")
	@Test
	void evaluateReturnsFalseWhenOperationIsFalse() 
			throws NotFoundException, BadOperationException {
		when(operationParser.getModelElements(anyChar(), anyString()))
			.thenReturn(ageMore30Array, sexEqualFemaleArray, ageLess30Array, sexEqualFemaleArray);
		patientEvaluator.setPatient(maleLess30);
		assertFalse(patientEvaluator.evaluate(ageMore30));
		patientEvaluator.setPatient(maleLess30);
		assertFalse(patientEvaluator.evaluate(sexEqualFemale));
		patientEvaluator.setPatient(maleMore30);
		assertFalse(patientEvaluator.evaluate(ageLess30));
		patientEvaluator.setPatient(maleLess30);
		assertFalse(patientEvaluator.evaluate(sexEqualFemale));

		when(operationParser.getModelElements(anyChar(), anyString()))
			.thenReturn(ageMore30Array, sexEqualMaleArray, ageLess30Array, sexEqualMaleArray);
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
	void evaluateThrowsOperatorNotFoundExceptionWhenAgeBadOperator() {
		String operation = "P.age + 30";
		List<String> parsed = Arrays.asList("age", "+", "30");
		when(operationParser.getModelElements(anyChar(), anyString())).thenReturn(parsed);
		patientEvaluator.setPatient(femaleMore30);
		assertThrows(NotFoundException.class, 
				() -> patientEvaluator.evaluate(operation));
		
	}
	
	@Test
	void evaluateThrowsOperatorNotFoundExceptionWhenSexBadOperator() {
		String operation = "P.sex + F";
		List<String> parsed = Arrays.asList("sex", "+", "F");
		when(operationParser.getModelElements(anyChar(), anyString())).thenReturn(parsed);
		patientEvaluator.setPatient(femaleLess30);
		assertThrows(NotFoundException.class, 
				() -> patientEvaluator.evaluate(operation));
		
	}
	
	@Test
	void evaluateThrowsFieldNotFoundExceptionWhenBadField() {
		String operation = "P.city = city";
		List<String> parsed = Arrays.asList("city", "=", "city");
		when(operationParser.getModelElements(anyChar(), anyString())).thenReturn(parsed);
		patientEvaluator.setPatient(femaleLess30);
		assertThrows(NotFoundException.class, 
				() -> patientEvaluator.evaluate(operation));
		
	}
	
	@Test
	void evaluateThrowsBadOperationExceptionWhenEmptyOperation() {
		String operation = "";
		List<String> parsed = List.of();
		when(operationParser.getModelElements(anyChar(), anyString())).thenReturn(parsed);
		patientEvaluator.setPatient(femaleLess30);
		assertThrows(BadOperationException.class, 
				() -> patientEvaluator.evaluate(operation));
		
	}
	
	@Test
	void evaluateThrowsBadOperationExceptionWhenBadOperation() {
		String operation = "P.age =";
		List<String> parsed = Arrays.asList("age", "=");
		when(operationParser.getModelElements(anyChar(), anyString())).thenReturn(parsed);
		patientEvaluator.setPatient(femaleLess30);
		assertThrows(BadOperationException.class, 
				() -> patientEvaluator.evaluate(operation));
		
	}

}
