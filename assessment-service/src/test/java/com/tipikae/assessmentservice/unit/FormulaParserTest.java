package com.tipikae.assessmentservice.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tipikae.assessmentservice.util.FormulaParserImpl;
import com.tipikae.assessmentservice.util.IFormulaParser;

class FormulaParserTest {
	
	private IFormulaParser formulaParser = new FormulaParserImpl();
	
	private static final String RIGHT_FORMULA1 = "[trigger = 2] AND [P.age < 30] OR [P.sex = M]";
	private static final String RIGHT_FORMULA2 = "[trigger = 2]AND[P.age < 30]OR[P.sex = M]";
	private static final String BAD_FORMULA = "trigger = 2 AND {P.age < 30} OR (P.sex = M)";

	@Test
	void getExpressionsReturnsNotEmptyWhenMatches() {
		assertEquals(3, formulaParser.getExpressions(RIGHT_FORMULA1).size());
		assertEquals(3, formulaParser.getExpressions(RIGHT_FORMULA2).size());
		assertEquals("trigger = 2", formulaParser.getExpressions(RIGHT_FORMULA1).get(0));
		assertEquals("P.age < 30", formulaParser.getExpressions(RIGHT_FORMULA1).get(1));
		assertEquals("P.sex = M", formulaParser.getExpressions(RIGHT_FORMULA1).get(2));
	}

	@Test
	void getExpressionsReturnsEmptyWhenNoMatches() {
		assertTrue(formulaParser.getExpressions(BAD_FORMULA).isEmpty());
	}

	@Test
	void getOperandesReturnsNotEmptyWhenMatches() {
		assertEquals(2, formulaParser.getOperandes(RIGHT_FORMULA1).size());
		assertEquals(2, formulaParser.getOperandes(RIGHT_FORMULA2).size());
		assertEquals("AND", formulaParser.getOperandes(RIGHT_FORMULA1).get(0));
		assertEquals("OR", formulaParser.getOperandes(RIGHT_FORMULA1).get(1));
	}

	@Test
	void getOperandesReturnsEmptyWhenNoMatches() {
		assertTrue(formulaParser.getOperandes(BAD_FORMULA).isEmpty());
	}

}
