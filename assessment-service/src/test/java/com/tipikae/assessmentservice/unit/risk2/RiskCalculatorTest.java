package com.tipikae.assessmentservice.unit.risk2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.repository.IFormulaRepository;
import com.tipikae.assessmentservice.risk2.FormulaParser;
import com.tipikae.assessmentservice.risk2.IEvaluator;
import com.tipikae.assessmentservice.risk2.RiskCalculatorImpl;

@ExtendWith(MockitoExtension.class)
class RiskCalculatorTest {
	
	@Mock
	private IFormulaRepository formulaRepository;
	
	@Mock
	private FormulaParser formulaParser;
	
	@Mock
	private IEvaluator evaluator;
	
	@InjectMocks
	private RiskCalculatorImpl riskCalculator;
	
	private static Patient patient;
	private static String operation1;
	private static String operation2;
	private static String operator;
	private static String formula;
	private static List<String> operations;
	private static List<String> operators;
	
	
	@BeforeAll
	private static void setUp() {
		patient = new Patient();
		operation1 = "trigger = 2";
		operation2 = "P.age < 30";
		operator = "AND";
		formula = operation1 + " " + operator + " " + operation2;
		operations = List.of(operation1, operation2);
		operators = List.of(operator);
	}

	@Test
	void calculateRisk() {
		
	}

}
