package com.tipikae.assessmentservice.unit.risk;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tipikae.assessmentservice.risk.OperationParser;

class OperationParserTest {
	
	private OperationParser operationParser = new OperationParser();
	
	private static final String RIGHT_MODEL_OPERATION = "P.age < 30";
	private static final String BAD_MODEL_OPERATION = "M.age + 30";
	private static final String RIGHT_METHOD_OPERATION = "count = 2";
	private static final String BAD_METHOD_OPERATION = "count$ = 2";

	@Test
	void getModelElementsReturnsElementsWhenFound() {
		assertEquals(3, operationParser.getElements(RIGHT_MODEL_OPERATION).size());
		assertEquals("age", operationParser.getElements(RIGHT_MODEL_OPERATION).get(0));
		assertEquals("<", operationParser.getElements(RIGHT_MODEL_OPERATION).get(1));
		assertEquals("30", operationParser.getElements(RIGHT_MODEL_OPERATION).get(2));
	}

	@Test
	void getModelElementsReturnsEmptyListWhenNotFound() {
		assertTrue(operationParser.getElements(BAD_MODEL_OPERATION).isEmpty());
	}

	@Test
	void getMethodElementsReturnsElementsWhenFound() {
		assertEquals(3, operationParser.getElements(RIGHT_METHOD_OPERATION).size());
		assertEquals("count", operationParser.getElements(RIGHT_METHOD_OPERATION).get(0));
		assertEquals("=", operationParser.getElements(RIGHT_METHOD_OPERATION).get(1));
		assertEquals("2", operationParser.getElements(RIGHT_METHOD_OPERATION).get(2));
	}

	@Test
	void getMethodElementsReturnsEmptyListWhenNotFound() {
		assertTrue(operationParser.getElements(BAD_METHOD_OPERATION).isEmpty());
	}

}
