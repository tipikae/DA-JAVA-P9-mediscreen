/**
 * 
 */
package com.tipikae.assessmentservice.risk.comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tipikae.assessmentservice.exception.OperandNotFoundException;

/**
 * Compare 2 elements.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class ComparatorImpl implements IComparator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ComparatorImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean compareInt(String operand, int a, int b) throws OperandNotFoundException {
		LOGGER.debug("compareInt: operand=" + operand + ", a=" + a + ", b=" + b);
		switch(operand) {
			case "=":
				return intEquals(a, b);
			case "<":
				return intLessThan(a, b);
			case "<=":
				return intLessThanOrEquals(a, b);
			case ">":
				return intGreaterThan(a, b);
			case ">=":
				return intGreaterThanOrEquals(a, b);
			case "!=":
				return intDifferents(a, b);
			default:
				throw new OperandNotFoundException("operand=" + operand + " not found");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean compareCharacter(String operand, char a, char b) throws OperandNotFoundException {
		LOGGER.debug("compareCharacter: operand=" + operand + ", a=" + a + ", b=" + b);
		switch(operand) {
			case "=":
				return charEquals(a, b);
			case "!=":
				return charDifferents(a, b);
			default:
				throw new OperandNotFoundException("operand=" + operand + " not found");
		}
	}
	
	private boolean intEquals(int a, int b) {
		return a == b;
	}
	
	private boolean intLessThan(int a, int b) {
		return a < b;
	}
	
	private boolean intLessThanOrEquals(int a, int b) {
		return a <= b;
	}
	
	private boolean intGreaterThan(int a, int b) {
		return a > b;
	}
	
	private boolean intGreaterThanOrEquals(int a, int b) {
		return a >= b;
	}
	
	private boolean intDifferents(int a, int b) {
		return a != b;
	}
	
	private boolean charEquals(char a, char b) {
		return a == b;
	}
	
	private boolean charDifferents(char a, char b) {
		return a != b;
	}

}
