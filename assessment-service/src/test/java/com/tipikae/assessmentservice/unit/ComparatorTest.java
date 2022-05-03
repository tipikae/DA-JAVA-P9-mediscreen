package com.tipikae.assessmentservice.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tipikae.assessmentservice.exception.OperandNotFoundException;
import com.tipikae.assessmentservice.risk.comparator.ComparatorImpl;
import com.tipikae.assessmentservice.risk.comparator.IComparator;

class ComparatorTest {
	
	private IComparator comparator = new ComparatorImpl();

	@Test
	void compareIntReturnsTrueWhenTrue() throws OperandNotFoundException {
		assertTrue(comparator.compareInt("=", 2, 2));
		assertTrue(comparator.compareInt("<", 1, 2));
		assertTrue(comparator.compareInt("<=", 1, 2));
		assertTrue(comparator.compareInt("<=", 2, 2));
		assertTrue(comparator.compareInt(">", 3, 2));
		assertTrue(comparator.compareInt(">=", 3, 2));
		assertTrue(comparator.compareInt(">=", 3, 3));
		assertTrue(comparator.compareInt("!=", 3, 2));
	}

	@Test
	void compareIntReturnsFalseWhenFalse() throws OperandNotFoundException {
		assertFalse(comparator.compareInt("=", 1, 2));
		assertFalse(comparator.compareInt("<", 3, 2));
		assertFalse(comparator.compareInt("<=", 3, 2));
		assertFalse(comparator.compareInt(">", 1, 2));
		assertFalse(comparator.compareInt(">=", 1, 2));
		assertFalse(comparator.compareInt("!=", 3, 3));
	}

	@Test
	void compareIntThrowsExceptionWhenNotFound() throws OperandNotFoundException {
		assertThrows(OperandNotFoundException.class, () -> comparator.compareInt("&", 1, 2));
	}

	@Test
	void compareCharacterReturnsTrueWhenTrue() throws OperandNotFoundException {
		assertTrue(comparator.compareCharacter("=", 'M', 'M'));
		assertTrue(comparator.compareCharacter("!=", 'M', 'F'));
	}

	@Test
	void compareCharacterReturnsFalseWhenFalse() throws OperandNotFoundException {
		assertFalse(comparator.compareCharacter("=", 'M', 'F'));
		assertFalse(comparator.compareCharacter("!=", 'M', 'M'));
	}

	@Test
	void compareCharacterThrowsExceptionWhenNotFound() throws OperandNotFoundException {
		assertThrows(OperandNotFoundException.class, () -> comparator.compareCharacter("&", 'M', 'F'));
	}

}
