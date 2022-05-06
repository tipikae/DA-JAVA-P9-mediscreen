package com.tipikae.assessmentservice.unit.risk;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tipikae.assessmentservice.risk.parser.ExpressionParserImpl;
import com.tipikae.assessmentservice.risk.parser.IExpressionParser;

class ExpressionParserTest {
	
	private IExpressionParser expressionParser = new ExpressionParserImpl();
	
	private static final String RIGHT_MODEL_EXPRESSION = "P.age < 30";
	private static final String BAD_MODEL_EXPRESSION = "M.age < 30";
	private static final String RIGHT_METHOD_EXPRESSION = "count = 2";
	private static final String BAD_METHOD_EXPRESSION = "count$ = 2";

	@Test
	void getModelElementsReturnsElementsWhenFound() {
		assertEquals(3, expressionParser.getModelElements('P', RIGHT_MODEL_EXPRESSION).size());
		assertEquals("age", expressionParser.getModelElements('P', RIGHT_MODEL_EXPRESSION).get(0));
		assertEquals("<", expressionParser.getModelElements('P', RIGHT_MODEL_EXPRESSION).get(1));
		assertEquals("30", expressionParser.getModelElements('P', RIGHT_MODEL_EXPRESSION).get(2));
	}

	@Test
	void getModelElementsReturnsEmptyListWhenNotFound() {
		assertTrue(expressionParser.getModelElements('P', BAD_MODEL_EXPRESSION).isEmpty());
	}

	@Test
	void getMethodElementsReturnsElementsWhenFound() {
		assertEquals(3, expressionParser.getMethodElements(RIGHT_METHOD_EXPRESSION).size());
		assertEquals("count", expressionParser.getMethodElements(RIGHT_METHOD_EXPRESSION).get(0));
		assertEquals("=", expressionParser.getMethodElements(RIGHT_METHOD_EXPRESSION).get(1));
		assertEquals("2", expressionParser.getMethodElements(RIGHT_METHOD_EXPRESSION).get(2));
	}

	@Test
	void getMethodElementsReturnsEmptyListWhenNotFound() {
		assertTrue(expressionParser.getMethodElements(BAD_METHOD_EXPRESSION).isEmpty());
	}

}
