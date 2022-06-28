package com.tipikae.assessmentservice.unit.risk;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tipikae.assessmentservice.risk.BooleanOperator;

class BooleanOperatorTest {

	@Test
	void apply() {
		assertTrue(BooleanOperator.AND.apply(true, true));
		assertFalse(BooleanOperator.AND.apply(true, false));
		assertFalse(BooleanOperator.AND.apply(false, false));

		assertTrue(BooleanOperator.OR.apply(true, true));
		assertTrue(BooleanOperator.OR.apply(false, true));
		assertFalse(BooleanOperator.OR.apply(false, false));
	}
	
	@Test
	void valueOf() {
		String and = "AND";
		String or = "OR";
		
		assertTrue(BooleanOperator.valueOfOperator(and).apply(true, true));
		assertFalse(BooleanOperator.valueOfOperator(and).apply(true, false));
		assertFalse(BooleanOperator.valueOfOperator(and).apply(false, false));

		assertTrue(BooleanOperator.valueOfOperator(or).apply(true, true));
		assertTrue(BooleanOperator.valueOfOperator(or).apply(false, true));
		assertFalse(BooleanOperator.valueOfOperator(or).apply(false, false));
		
		assertNull(BooleanOperator.valueOfOperator("XOR"));
	}

}
