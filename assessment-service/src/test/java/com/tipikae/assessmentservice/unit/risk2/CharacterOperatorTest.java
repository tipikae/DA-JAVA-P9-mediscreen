package com.tipikae.assessmentservice.unit.risk2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tipikae.assessmentservice.risk2.CharacterOperator;

class CharacterOperatorTest {
	
	private static char male = 'M';
	private static char female = 'F';
	
	@Test
	void apply() {
		assertTrue(CharacterOperator.EQUALS.apply(male, male));
		assertFalse(CharacterOperator.EQUALS.apply(male, female));

		assertFalse(CharacterOperator.DIFFERENTS.apply(male, male));
		assertTrue(CharacterOperator.DIFFERENTS.apply(male, female));
	}
	
	@Test
	void valueOf() {
		String equals = "=";
		String differents = "!=";
		
		assertTrue(CharacterOperator.valueOfOperator(equals).apply(male, male));
		assertFalse(CharacterOperator.valueOfOperator(equals).apply(male, female));

		assertFalse(CharacterOperator.valueOfOperator(differents).apply(male, male));
		assertTrue(CharacterOperator.valueOfOperator(differents).apply(male, female));
		
		assertNull(CharacterOperator.valueOfOperator("++"));
	}

}
