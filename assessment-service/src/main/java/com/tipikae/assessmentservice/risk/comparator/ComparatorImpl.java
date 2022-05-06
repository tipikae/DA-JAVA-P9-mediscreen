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
	private static final String EQUALS = "=";
	private static final String LESS_THAN = "<";
	private static final String LESS_THAN_OR_EQUALS1 = "<=";
	private static final String LESS_THAN_OR_EQUALS2 = "=<";
	private static final String GREATER_THAN = ">";
	private static final String GREATER_THAN_OR_EQUALS1 = ">=";
	private static final String GREATER_THAN_OR_EQUALS2 = "=>";
	private static final String DIFFERENTS = "!=";
	private static final String AND = "AND";
	private static final String OR = "OR";

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean compareInt(String operand, int a, int b) throws OperandNotFoundException {
		LOGGER.debug("compareInt: operand=" + operand + ", a=" + a + ", b=" + b);
		switch(operand) {
			case EQUALS:
				return intEquals(a, b);
			case LESS_THAN:
				return intLessThan(a, b);
			case LESS_THAN_OR_EQUALS1:
				return intLessThanOrEquals(a, b);
			case LESS_THAN_OR_EQUALS2:
				return intLessThanOrEquals(a, b);
			case GREATER_THAN:
				return intGreaterThan(a, b);
			case GREATER_THAN_OR_EQUALS1:
				return intGreaterThanOrEquals(a, b);
			case GREATER_THAN_OR_EQUALS2:
				return intGreaterThanOrEquals(a, b);
			case DIFFERENTS:
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
		operand = operand.toUpperCase();
		switch(operand) {
			case EQUALS:
				return charEquals(a, b);
			case DIFFERENTS:
				return charDifferents(a, b);
			default:
				throw new OperandNotFoundException("operand=" + operand + " not found");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean compareBoolean(String operand, boolean a, boolean b) throws OperandNotFoundException {
		LOGGER.debug("compareBoolean: operand=" + operand + ", a=" + a + ", b=" + b);
		operand = operand.toUpperCase();
		switch(operand) {
			case AND:
				return and(a, b);
			case OR:
				return or(a, b);
			default:
				throw new OperandNotFoundException("operand=" + operand + " not found");
		}
	}
	
	private boolean intEquals(int a, int b) {
		boolean result = (a == b);
		LOGGER.debug("intEquals: a=" + a + ", b=" + b + " => " + result);
		return result;
	}
	
	private boolean intLessThan(int a, int b) {
		boolean result = (a < b);
		LOGGER.debug("intLessThan: a=" + a + ", b=" + b + " => " + result);
		return result;
	}
	
	private boolean intLessThanOrEquals(int a, int b) {
		boolean result = (a <= b);
		LOGGER.debug("intLessThanOrEquals: a=" + a + ", b=" + b + " => " + result);
		return result;
	}
	
	private boolean intGreaterThan(int a, int b) {
		boolean result = (a > b);
		LOGGER.debug("intGreaterThan: a=" + a + ", b=" + b + " => " + result);
		return result;
	}
	
	private boolean intGreaterThanOrEquals(int a, int b) {
		boolean result = (a >= b);
		LOGGER.debug("intGreaterThanOrEquals: a=" + a + ", b=" + b + " => " + result);
		return result;
	}
	
	private boolean intDifferents(int a, int b) {
		boolean result = (a != b);
		LOGGER.debug("intDifferents: a=" + a + ", b=" + b + " => " + result);
		return result;
	}
	
	private boolean charEquals(char a, char b) {
		boolean result = (a == b);
		LOGGER.debug("charEquals: a=" + a + ", b=" + b + " => " + result);
		return result;
	}
	
	private boolean charDifferents(char a, char b) {
		boolean result = (a != b);
		LOGGER.debug("charDifferents: a=" + a + ", b=" + b + " => " + result);
		return result;
	}
	
	private boolean and(boolean a, boolean b) {
		boolean result = (a && b);
		LOGGER.debug("and: a=" + a + ", b=" + b + " => " + result);
		return result;
	}
	
	private boolean or(boolean a, boolean b) {
		boolean result = (a || b);
		LOGGER.debug("or: a=" + a + ", b=" + b + " => " + result);
		return result;
	}

}
