package com.tipikae.assessmentservice.unit.risk2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tipikae.assessmentservice.risk2.FormulaParser;

class FormulaParserTest {
	
	private FormulaParser formulaParser = new FormulaParser();
	
	private static final String RIGHT_FORMULA1 = "[trigger = 2] AND [P.age < 30] OR [P.sex = M]";
	private static final String RIGHT_FORMULA2 = "[trigger = 2]AND[P.age < 30]OR[P.sex = M]";
	private static final String BAD_FORMULA = "trigger = 2 AND {P.age < 30} OR (P.sex = M)";

	@Test
	void getOperationsReturnsNotEmptyWhenMatches() {
		assertEquals(3, formulaParser.getOperations(RIGHT_FORMULA1).size());
		assertEquals(3, formulaParser.getOperations(RIGHT_FORMULA2).size());
		assertEquals("trigger = 2", formulaParser.getOperations(RIGHT_FORMULA1).get(0));
		assertEquals("P.age < 30", formulaParser.getOperations(RIGHT_FORMULA1).get(1));
		assertEquals("P.sex = M", formulaParser.getOperations(RIGHT_FORMULA1).get(2));
	}

	@Test
	void getOperationsReturnsEmptyWhenNoMatches() {
		assertTrue(formulaParser.getOperations(BAD_FORMULA).isEmpty());
	}

	@Test
	void getOperandesReturnsNotEmptyWhenMatches() {
		assertEquals(2, formulaParser.getOperators(RIGHT_FORMULA1).size());
		assertEquals(2, formulaParser.getOperators(RIGHT_FORMULA2).size());
		assertEquals("AND", formulaParser.getOperators(RIGHT_FORMULA1).get(0));
		assertEquals("OR", formulaParser.getOperators(RIGHT_FORMULA1).get(1));
	}

	@Test
	void getOperandesReturnsEmptyWhenNoMatches() {
		assertTrue(formulaParser.getOperators(BAD_FORMULA).isEmpty());
	}

}
