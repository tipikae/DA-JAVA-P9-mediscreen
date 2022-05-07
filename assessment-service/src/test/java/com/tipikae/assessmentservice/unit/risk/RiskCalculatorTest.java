package com.tipikae.assessmentservice.unit.risk;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tipikae.assessmentservice.exception.ExpressionValidationException;
import com.tipikae.assessmentservice.exception.OperandNotFoundException;
import com.tipikae.assessmentservice.exception.RiskNotFoundException;
import com.tipikae.assessmentservice.exception.ValidatorNotFoundException;
import com.tipikae.assessmentservice.model.Formula;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.repository.IFormulaRepository;
import com.tipikae.assessmentservice.risk.RiskCalculatorImpl;
import com.tipikae.assessmentservice.risk.comparator.IComparator;
import com.tipikae.assessmentservice.risk.core.IEvaluator;
import com.tipikae.assessmentservice.risk.parser.IFormulaParser;

@ExtendWith(MockitoExtension.class)
class RiskCalculatorTest {
	
	@Mock
	private IFormulaRepository formulaRepository;
	
	@Mock
	private IFormulaParser formulaParser;
	
	@Mock
	private IEvaluator evaluator;
	
	@Mock
	private IComparator comparator;
	
	@InjectMocks
	private RiskCalculatorImpl riskCalculator;
	
	private static Patient patient;
	private static String riskNone;
	private static String expression1;
	private static String expression2;
	private static String operand;
	private static Formula rightFormula;
	private static Formula badFormula;
	private static List<Formula> rightFormulas;
	private static List<Formula> badFormulas;
	
	@BeforeAll
	private static void setUp() {
		patient = new Patient(1L, "", "", LocalDate.of(2000, 01, 01), 'F', "", "");
		riskNone = "None";
		expression1 = "trigger = 2";
		expression2 = "age < 30";
		operand = "AND";
		rightFormula = new Formula(1L, riskNone, expression1 + operand + expression2);
		badFormula = new Formula(1L, riskNone, "expression1 + operand");
		rightFormulas = List.of(rightFormula);
		badFormulas = List.of(badFormula);
	}

	@Test
	void calculateRiskReturnsRiskWhenOk() 
			throws ValidatorNotFoundException, ExpressionValidationException, OperandNotFoundException, 
			RiskNotFoundException {
		when(formulaRepository.findAll()).thenReturn(rightFormulas);
		when(formulaParser.getExpressions(anyString())).thenReturn(List.of(expression1, expression2));
		when(formulaParser.getOperands(anyString())).thenReturn(List.of(operand));
		when(evaluator.evaluateExpression(any(Patient.class), anyString())).thenReturn(true, true);
		when(comparator.compareBoolean(anyString(), anyBoolean(), anyBoolean())).thenReturn(true);
		assertEquals("None", riskCalculator.calculateRisk(patient));
	}

	@Test
	void calculateRiskThrowsRiskNotFoundExceptionWhenBadFormulas() {
		when(formulaRepository.findAll()).thenReturn(badFormulas);
		when(formulaParser.getExpressions(anyString())).thenReturn(List.of(expression1));
		when(formulaParser.getOperands(anyString())).thenReturn(List.of(operand));
		assertThrows(RiskNotFoundException.class, () -> riskCalculator.calculateRisk(patient));
	}

	@SuppressWarnings("unchecked")
	@Test
	void calculateRiskThrowsRiskNotFoundExceptionWhenMultipleRightFormulas() 
			throws ValidatorNotFoundException, ExpressionValidationException, OperandNotFoundException {
		when(formulaRepository.findAll()).thenReturn(List.of(rightFormula, rightFormula));
		when(formulaParser.getExpressions(anyString()))
			.thenReturn(List.of(expression1, expression2), List.of(expression1, expression2));
		when(formulaParser.getOperands(anyString())).thenReturn(List.of(operand), List.of(operand));
		when(evaluator.evaluateExpression(any(Patient.class), anyString()))
			.thenReturn(true, true, true, true);
		when(comparator.compareBoolean(anyString(), anyBoolean(), anyBoolean())).thenReturn(true, true);
		assertThrows(RiskNotFoundException.class, () -> riskCalculator.calculateRisk(patient));
	}

	@Test
	void calculateRiskThrowsRiskNotFoundExceptionWhenEvaluatorfailed() 
			throws ValidatorNotFoundException, ExpressionValidationException {
		when(formulaRepository.findAll()).thenReturn(rightFormulas);
		when(formulaParser.getExpressions(anyString())).thenReturn(List.of(expression1, expression2));
		when(formulaParser.getOperands(anyString())).thenReturn(List.of(operand));
		doThrow(ValidatorNotFoundException.class)
			.when(evaluator).evaluateExpression(any(Patient.class), anyString());
		assertThrows(RiskNotFoundException.class, () -> riskCalculator.calculateRisk(patient));
	}

	@Test
	void calculateRiskThrowsRiskNotFoundExceptionWhenComparatorfailed() 
			throws ValidatorNotFoundException, ExpressionValidationException, OperandNotFoundException {
		when(formulaRepository.findAll()).thenReturn(rightFormulas);
		when(formulaParser.getExpressions(anyString())).thenReturn(List.of(expression1, expression2));
		when(formulaParser.getOperands(anyString())).thenReturn(List.of(operand));
		when(evaluator.evaluateExpression(any(Patient.class), anyString())).thenReturn(true, true);
		doThrow(OperandNotFoundException.class)
			.when(comparator).compareBoolean(anyString(), anyBoolean(), anyBoolean());
		assertThrows(RiskNotFoundException.class, () -> riskCalculator.calculateRisk(patient));
	}

}
