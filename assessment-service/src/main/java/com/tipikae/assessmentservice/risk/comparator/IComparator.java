/**
 * 
 */
package com.tipikae.assessmentservice.risk.comparator;

import com.tipikae.assessmentservice.exception.OperandNotFoundException;

/**
 * Compare 2 elements.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IComparator {

	/**
	 * Compare 2 integers.
	 * @param operand String
	 * @param a int
	 * @param b int
	 * @return boolean
	 * @throws OperandNotFoundException 
	 */
	boolean compareInt(String operand, int a, int b) throws OperandNotFoundException;
	
	/**
	 * Compare 2 characters.
	 * @param operand String
	 * @param a char
	 * @param b char
	 * @return String
	 * @throws OperandNotFoundException
	 */
	boolean compareCharacter(String operand, char a, char b) throws OperandNotFoundException;
	
	/**
	 * Compare 2 boolean.
	 * @param operand String
	 * @param a boolean
	 * @param b boolean
	 * @return boolean
	 * @throws OperandNotFoundException
	 */
	boolean compareBoolean(String operand, boolean a, boolean b) throws OperandNotFoundException;
}
