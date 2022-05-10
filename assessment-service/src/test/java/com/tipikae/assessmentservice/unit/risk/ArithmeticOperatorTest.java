package com.tipikae.assessmentservice.unit.risk;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tipikae.assessmentservice.risk.ArithmeticOperator;

class ArithmeticOperatorTest {

	@Test
	void apply() {
		assertTrue(ArithmeticOperator.LESS_THAN.apply(1, 2));
		assertFalse(ArithmeticOperator.LESS_THAN.apply(2, 2));
		assertFalse(ArithmeticOperator.LESS_THAN.apply(3, 2));
		
		assertTrue(ArithmeticOperator.LESS_THAN_OR_EQUALS.apply(1, 2));
		assertTrue(ArithmeticOperator.LESS_THAN_OR_EQUALS.apply(2, 2));
		assertFalse(ArithmeticOperator.LESS_THAN_OR_EQUALS.apply(3, 2));
		
		assertFalse(ArithmeticOperator.GREATER_THAN.apply(1, 2));
		assertFalse(ArithmeticOperator.GREATER_THAN.apply(2, 2));
		assertTrue(ArithmeticOperator.GREATER_THAN.apply(3, 2));
		
		assertFalse(ArithmeticOperator.GREATER_THAN_OR_EQUALS.apply(1, 2));
		assertTrue(ArithmeticOperator.GREATER_THAN_OR_EQUALS.apply(2, 2));
		assertTrue(ArithmeticOperator.GREATER_THAN_OR_EQUALS.apply(3, 2));
		
		assertTrue(ArithmeticOperator.EQUALS.apply(1, 1));
		assertFalse(ArithmeticOperator.EQUALS.apply(1, 2));
		
		assertFalse(ArithmeticOperator.DIFFERENTS.apply(1, 1));
		assertTrue(ArithmeticOperator.DIFFERENTS.apply(1, 2));
	}
	
	@Test
	void valueOf() {
		String lessThan = "<";
		String lessThanOrEquals = "<=";
		String greaterThan = ">";
		String greaterThanOrEquals = ">=";
		String equals = "=";
		String differents = "!=";
		
		assertTrue(ArithmeticOperator.valueOfOperator(lessThan).apply(1, 2));
		assertFalse(ArithmeticOperator.valueOfOperator(lessThan).apply(2, 2));
		assertFalse(ArithmeticOperator.valueOfOperator(lessThan).apply(3, 2));
		
		assertTrue(ArithmeticOperator.valueOfOperator(lessThanOrEquals).apply(1, 2));
		assertTrue(ArithmeticOperator.valueOfOperator(lessThanOrEquals).apply(2, 2));
		assertFalse(ArithmeticOperator.valueOfOperator(lessThanOrEquals).apply(3, 2));
		
		assertFalse(ArithmeticOperator.valueOfOperator(greaterThan).apply(1, 2));
		assertFalse(ArithmeticOperator.valueOfOperator(greaterThan).apply(2, 2));
		assertTrue(ArithmeticOperator.valueOfOperator(greaterThan).apply(3, 2));
		
		assertFalse(ArithmeticOperator.valueOfOperator(greaterThanOrEquals).apply(1, 2));
		assertTrue(ArithmeticOperator.valueOfOperator(greaterThanOrEquals).apply(2, 2));
		assertTrue(ArithmeticOperator.valueOfOperator(greaterThanOrEquals).apply(3, 2));
		
		assertTrue(ArithmeticOperator.valueOfOperator(equals).apply(1, 1));
		assertFalse(ArithmeticOperator.valueOfOperator(equals).apply(1, 2));
		
		assertFalse(ArithmeticOperator.valueOfOperator(differents).apply(1, 1));
		assertTrue(ArithmeticOperator.valueOfOperator(differents).apply(1, 2));
		
		assertNull(ArithmeticOperator.valueOfOperator("++"));
	}

}
