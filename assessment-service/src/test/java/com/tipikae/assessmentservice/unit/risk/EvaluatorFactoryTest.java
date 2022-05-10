package com.tipikae.assessmentservice.unit.risk;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tipikae.assessmentservice.risk.EvaluatorFactory;
import com.tipikae.assessmentservice.risk.PatientEvaluator;
import com.tipikae.assessmentservice.risk.TriggerEvaluator;

@ExtendWith(MockitoExtension.class)
class EvaluatorFactoryTest {
	
	@Mock
	private PatientEvaluator patientEvaluator;
	
	@Mock
	private TriggerEvaluator triggerEvaluator;
	
	@InjectMocks
	private EvaluatorFactory evaluatorFactory;
	
	private static String patientOperation;
	private static String triggerOperation;
	
	@BeforeAll
	private static void setUp() {
		patientOperation = "P.age < 30";
		triggerOperation = "trigger = 2";
	}

	@Test
	void getEvaluatorReturnsPatientEvaluatorWhenPatientOperation() {
		assertEquals(patientEvaluator.getClass(), 
				evaluatorFactory.getEvaluator(patientOperation).getClass());
	}

	@Test
	void getEvaluatorReturnsTriggerEvaluatorWhenTriggerOperation() {
		assertEquals(triggerEvaluator.getClass(), 
				evaluatorFactory.getEvaluator(triggerOperation).getClass());
	}

}
