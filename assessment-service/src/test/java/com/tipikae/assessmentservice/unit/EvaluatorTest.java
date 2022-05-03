package com.tipikae.assessmentservice.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.assessmentservice.exception.ExpressionValidationException;
import com.tipikae.assessmentservice.risk.IEvaluator;
import com.tipikae.assessmentservice.risk.ModelEvaluator;

@SpringBootTest
class EvaluatorTest {
	
	@Test
	void evaluateExpressionReturnsTrueWhenTrue() throws ExpressionValidationException {
		IEvaluator evaluator = new ModelEvaluator(29, 'M');
		assertTrue(evaluator.evaluateExpression("P.age < 30"));
		assertTrue(evaluator.evaluateExpression("P.sex = M"));
	}
	
	@Test
	void evaluateExpressionReturnsFalseWhenFalse() throws ExpressionValidationException {
		IEvaluator evaluator = new ModelEvaluator(29, 'M');
		assertFalse(evaluator.evaluateExpression("P.age > 30"));
		assertFalse(evaluator.evaluateExpression("P.sex = F"));
	}
	
	@Test
	void evaluateExpressionThrowsExceptionWhenError() {
		IEvaluator evaluator = new ModelEvaluator(29, 'M');
		assertThrows(ExpressionValidationException.class, () -> evaluator.evaluateExpression("M.age > 30"));
		assertThrows(ExpressionValidationException.class, () -> evaluator.evaluateExpression("P.age & 30"));
		assertThrows(ExpressionValidationException.class, () -> evaluator.evaluateExpression("P.field > 30"));
	}

}
