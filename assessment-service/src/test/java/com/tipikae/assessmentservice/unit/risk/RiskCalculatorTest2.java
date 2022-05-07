package com.tipikae.assessmentservice.unit.risk;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
import com.tipikae.assessmentservice.model.Risk;
import com.tipikae.assessmentservice.repository.IFormulaRepository;
import com.tipikae.assessmentservice.repository.IRiskRepository;
import com.tipikae.assessmentservice.risk.RiskCalculatorImpl2;
import com.tipikae.assessmentservice.risk.comparator.IComparator;
import com.tipikae.assessmentservice.risk.core.IEvaluator;
import com.tipikae.assessmentservice.risk.parser.IFormulaParser;

@ExtendWith(MockitoExtension.class)
class RiskCalculatorTest2 {
	
	@Mock
	private IRiskRepository riskRepository;
	
	@Mock
	private IFormulaRepository formulaRepository;
	
	@Mock
	private IFormulaParser formulaParser;
	
	@Mock
	private IEvaluator evaluator;
	
	@Mock
	private IComparator comparator;
	
	@InjectMocks
	private RiskCalculatorImpl2 riskCalculator;
	
	private static Patient patient;
	private static String labelSingleExpression;
	private static String label2ExpressionsWithAND;
	private static String label2ExpressionsWithOR;
	private static String labelMultipleExpressionsWithParenthesis;
	private static String labelMultipleExpressionsWithDoubleParenthesis;
	private static String labelSingleAndMultipleExpressionsWithDoubleParenthesis;
	
	
	@BeforeAll
	private static void setUp() {
		patient = new Patient(1L, "", "", LocalDate.of(2000, 01, 01), 'F', "", "");
		labelSingleExpression = 
				"[trigger < 2]";
		label2ExpressionsWithAND = 
				"[trigger = 2] AND [P.age < 30]";
		label2ExpressionsWithOR = 
				"[trigger = 5] OR [trigger = 6]";
		labelMultipleExpressionsWithParenthesis = 
				"[trigger < 2] "
				+ "OR ([trigger = 2] AND [P.age < 30])";
		labelMultipleExpressionsWithDoubleParenthesis = 
				"([trigger = 4] AND [P.age < 30]) "
				+ "OR (([trigger = 5] OR [trigger = 6]) AND [P.age < 30] AND [P.sex = F])";
		labelSingleAndMultipleExpressionsWithDoubleParenthesis = 
				"[trigger < 2] "
				+ "OR ([trigger = 3] AND [P.age < 30] AND [P.sex = M]) "
				+ "OR ([trigger = 4] AND [P.age < 30]) "
				+ "OR (([trigger = 5] OR [trigger = 6]) AND [P.age < 30] AND [P.sex = F]) "
				+ "OR (([trigger = 6] OR [trigger = 7]) AND [P.age >= 30])";
	}

	@Test
	void calculateRiskReturnsRiskWhenOk() 
			throws ValidatorNotFoundException, ExpressionValidationException, OperandNotFoundException, 
			RiskNotFoundException {
		
	}

	@Test
	void calculateRiskThrowsRiskNotFoundExceptionWhenBadFormulas() {
		
	}

	@Test
	void calculateRiskThrowsRiskNotFoundExceptionWhenMultipleRightFormulas() 
			throws ValidatorNotFoundException, ExpressionValidationException, OperandNotFoundException {
		
	}

	@Test
	void calculateRiskThrowsRiskNotFoundExceptionWhenEvaluatorfailed() 
			throws ValidatorNotFoundException, ExpressionValidationException {
		
	}

	@Test
	void calculateRiskThrowsRiskNotFoundExceptionWhenComparatorfailed() 
			throws ValidatorNotFoundException, ExpressionValidationException, OperandNotFoundException {
		
	}

}
