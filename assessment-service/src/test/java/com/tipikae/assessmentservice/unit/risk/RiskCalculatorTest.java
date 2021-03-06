package com.tipikae.assessmentservice.unit.risk;

import static org.junit.jupiter.api.Assertions.*;
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

import com.tipikae.assessmentservice.exception.BadOperationException;
import com.tipikae.assessmentservice.exception.ClientException;
import com.tipikae.assessmentservice.exception.NotFoundException;
import com.tipikae.assessmentservice.model.Formula;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.repository.IFormulaRepository;
import com.tipikae.assessmentservice.risk.EvaluatorFactory;
import com.tipikae.assessmentservice.risk.FormulaValidator;
import com.tipikae.assessmentservice.risk.IEvaluator;
import com.tipikae.assessmentservice.risk.RiskCalculatorImpl;

@ExtendWith(MockitoExtension.class)
class RiskCalculatorTest {
	
	@Mock
	private IFormulaRepository formulaRepository;
	
	@Mock
	private FormulaValidator formulaValidator;
	
	@Mock
	private EvaluatorFactory evaluatorFactory;
	
	@Mock
	private IEvaluator evaluator;
	
	@InjectMocks
	private RiskCalculatorImpl riskCalculator;
	
	private static Patient patient;
	private static String operation1;
	private static String operation2;
	private static String operation3;
	private static String operator1;
	private static String operator2;
	private static String rightForm;
	private static String badForm;
	private static String singleForm;
	private static String risk = "None";
	private static Formula rightFormula;
	private static Formula badFormula;
	private static Formula singleFormula;
	
	
	@BeforeAll
	private static void setUp() {
		patient = new Patient();
		operation1 = "[trigger = 2]";
		operation2 = "[P.age < 30]";
		operation3 = "[P.sex = M]";
		operator1 = "OR";
		operator2 = "AND";
		rightForm = operation1 + " " + operator1 + " " + operation2 + " " + operator2 + " " + operation3;
		badForm = operation1 + operation2;
		singleForm = operation1;
		rightFormula = new Formula(0, risk, rightForm);
		badFormula = new Formula(0, risk, badForm);
		singleFormula = new Formula(0, risk, singleForm);
	}

	@Test
	void calculateRiskReturnsRiskWhenOneFormulaValidAndEvaluated() 
			throws NotFoundException, BadOperationException, ClientException {
		when(formulaRepository.findAll()).thenReturn(List.of(badFormula, rightFormula));
		when(formulaValidator.validate(anyString())).thenReturn(false, true);
		when(evaluatorFactory.getEvaluator(anyString())).thenReturn(evaluator, evaluator, evaluator);
		when(evaluator.evaluate(anyString())).thenReturn(false, true, true);
		assertEquals(risk, riskCalculator.calculateRisk(patient));
	}
	
	@Test
	void calculateRiskThrowsRiskNotFoundExceptionWhenOneFormulaValidAndNotEvaluated() 
			throws NotFoundException, BadOperationException, ClientException {
		when(formulaRepository.findAll()).thenReturn(List.of(badFormula, rightFormula));
		when(formulaValidator.validate(anyString())).thenReturn(false, true);
		when(evaluatorFactory.getEvaluator(anyString())).thenReturn(evaluator, evaluator, evaluator);	
		when(evaluator.evaluate(anyString())).thenReturn(false, false, true);
		assertThrows(NotFoundException.class, () -> riskCalculator.calculateRisk(patient));
	}
	
	@Test
	void calculateRiskThrowsRiskNotFoundExceptionWhenOneSingleFormulaValidAndNotEvaluated() 
			throws NotFoundException, BadOperationException, ClientException {
		when(formulaRepository.findAll()).thenReturn(List.of(singleFormula));
		when(formulaValidator.validate(anyString())).thenReturn(true);
		when(evaluatorFactory.getEvaluator(anyString())).thenReturn(evaluator);	
		when(evaluator.evaluate(anyString())).thenReturn(false);
		assertThrows(NotFoundException.class, () -> riskCalculator.calculateRisk(patient));
	}
	
	@Test
	void calculateRiskThrowsRiskNotFoundExceptionWhenEvaluatorError() 
			throws NotFoundException, BadOperationException, ClientException {
		when(formulaRepository.findAll()).thenReturn(List.of(badFormula, rightFormula));
		when(formulaValidator.validate(anyString())).thenReturn(false, true);
		when(evaluatorFactory.getEvaluator(anyString())).thenReturn(evaluator, evaluator, evaluator);
		doThrow(NotFoundException.class)
			.when(evaluator).evaluate(anyString());
		assertThrows(NotFoundException.class, () -> riskCalculator.calculateRisk(patient));
	}
	
	@Test
	void calculateRiskThrowsRiskNotFoundExceptionWhenNoFormula() {
		when(formulaRepository.findAll()).thenReturn(List.of());
		assertThrows(NotFoundException.class, () -> riskCalculator.calculateRisk(patient));
	}
	
	@Test
	void calculateRiskThrowsRiskNotFoundExceptionWhenNoFormulaValid() {
		when(formulaRepository.findAll()).thenReturn(List.of(badFormula, badFormula));
		when(formulaValidator.validate(anyString())).thenReturn(false, false);
		assertThrows(NotFoundException.class, () -> riskCalculator.calculateRisk(patient));
	}
	
	@Test
	void calculateRiskThrowsRiskNotFoundExceptionWhenOperationsAndOperatorsSizeMismatched() {
		when(formulaRepository.findAll()).thenReturn(List.of(badFormula, rightFormula));
		when(formulaValidator.validate(anyString())).thenReturn(false, true);
		assertThrows(NotFoundException.class, () -> riskCalculator.calculateRisk(patient));
	}

}
