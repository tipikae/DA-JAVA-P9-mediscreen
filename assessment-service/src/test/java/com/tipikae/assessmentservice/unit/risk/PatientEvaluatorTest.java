package com.tipikae.assessmentservice.unit.risk;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
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

import com.tipikae.assessmentservice.exception.BadOperationException2;
import com.tipikae.assessmentservice.exception.FieldNotFoundException2;
import com.tipikae.assessmentservice.exception.OperatorNotFoundException2;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.risk.OperationParser;
import com.tipikae.assessmentservice.risk.PatientEvaluator;
import com.tipikae.assessmentservice.service.AgeProvider;

@ExtendWith(MockitoExtension.class)
class PatientEvaluatorTest {
	
	@Mock
	private AgeProvider ageProvider;
	
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
			throws OperatorNotFoundException2, FieldNotFoundException2, BadOperationException2 {
		when(ageProvider.calculateAge(any(LocalDate.class))).thenReturn(22, 42, 22, 42);
		when(operationParser.getModelElements(anyChar(), anyString()))
			.thenReturn(ageLess30Array, sexEqualMaleArray, ageMore30Array, sexEqualMaleArray);
		assertTrue(patientEvaluator.evaluate(maleLess30, ageLess30));
		assertTrue(patientEvaluator.evaluate(maleLess30, sexEqualMale));
		assertTrue(patientEvaluator.evaluate(maleMore30, ageMore30));
		assertTrue(patientEvaluator.evaluate(maleMore30, sexEqualMale));


		when(operationParser.getModelElements(anyChar(), anyString()))
			.thenReturn(ageLess30Array, sexEqualFemaleArray, ageMore30Array, sexEqualFemaleArray);
		assertTrue(patientEvaluator.evaluate(femaleLess30, ageLess30));
		assertTrue(patientEvaluator.evaluate(femaleLess30, sexEqualFemale));
		assertTrue(patientEvaluator.evaluate(femaleMore30, ageMore30));
		assertTrue(patientEvaluator.evaluate(femaleMore30, sexEqualFemale));
	}

	@SuppressWarnings("unchecked")
	@Test
	void evaluateReturnsFalseWhenOperationIsFalse() 
			throws OperatorNotFoundException2, FieldNotFoundException2, BadOperationException2 {
		when(ageProvider.calculateAge(any(LocalDate.class))).thenReturn(22, 42, 22, 42);
		when(operationParser.getModelElements(anyChar(), anyString()))
			.thenReturn(ageMore30Array, sexEqualFemaleArray, ageLess30Array, sexEqualFemaleArray);
		assertFalse(patientEvaluator.evaluate(maleLess30, ageMore30));
		assertFalse(patientEvaluator.evaluate(maleLess30, sexEqualFemale));
		assertFalse(patientEvaluator.evaluate(maleMore30, ageLess30));
		assertFalse(patientEvaluator.evaluate(maleMore30, sexEqualFemale));

		when(operationParser.getModelElements(anyChar(), anyString()))
			.thenReturn(ageMore30Array, sexEqualMaleArray, ageLess30Array, sexEqualMaleArray);
		assertFalse(patientEvaluator.evaluate(femaleLess30, ageMore30));
		assertFalse(patientEvaluator.evaluate(femaleLess30, sexEqualMale));
		assertFalse(patientEvaluator.evaluate(femaleMore30, ageLess30));
		assertFalse(patientEvaluator.evaluate(femaleMore30, sexEqualMale));
	}
	
	@Test
	void evaluateThrowsOperatorNotFoundException2WhenAgeBadOperator() {
		String operation = "P.age + 30";
		List<String> parsed = Arrays.asList("age", "+", "30");
		when(ageProvider.calculateAge(any(LocalDate.class))).thenReturn(22);
		when(operationParser.getModelElements(anyChar(), anyString())).thenReturn(parsed);
		assertThrows(OperatorNotFoundException2.class, 
				() -> patientEvaluator.evaluate(maleLess30, operation));
		
	}
	
	@Test
	void evaluateThrowsOperatorNotFoundException2WhenSexBadOperator() {
		String operation = "P.sex + F";
		List<String> parsed = Arrays.asList("sex", "+", "F");
		when(operationParser.getModelElements(anyChar(), anyString())).thenReturn(parsed);
		assertThrows(OperatorNotFoundException2.class, 
				() -> patientEvaluator.evaluate(femaleLess30, operation));
		
	}
	
	@Test
	void evaluateThrowsFieldNotFoundException2WhenBadField() {
		String operation = "P.city = city";
		List<String> parsed = Arrays.asList("city", "=", "city");
		when(operationParser.getModelElements(anyChar(), anyString())).thenReturn(parsed);
		assertThrows(FieldNotFoundException2.class, 
				() -> patientEvaluator.evaluate(femaleLess30, operation));
		
	}
	
	@Test
	void evaluateThrowsBadOperationException2WhenEmptyOperation() {
		String operation = "";
		List<String> parsed = List.of();
		when(operationParser.getModelElements(anyChar(), anyString())).thenReturn(parsed);
		assertThrows(BadOperationException2.class, 
				() -> patientEvaluator.evaluate(femaleLess30, operation));
		
	}
	
	@Test
	void evaluateThrowsBadOperationException2WhenBadOperation() {
		String operation = "P.age =";
		List<String> parsed = Arrays.asList("age", "=");
		when(operationParser.getModelElements(anyChar(), anyString())).thenReturn(parsed);
		assertThrows(BadOperationException2.class, 
				() -> patientEvaluator.evaluate(femaleLess30, operation));
		
	}

}
