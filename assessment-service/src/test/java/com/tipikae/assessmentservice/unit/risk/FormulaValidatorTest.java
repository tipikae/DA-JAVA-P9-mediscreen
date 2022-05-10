package com.tipikae.assessmentservice.unit.risk;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.tipikae.assessmentservice.risk.FormulaValidator;

class FormulaValidatorTest {
	
	private FormulaValidator formulaValidator = new FormulaValidator();
	
	private static String rightFormula1;
	private static String rightFormula2;
	private static String rightFormula3;
	private static String rightFormula4;
	private static String rightFormula5;
	private static String rightFormula6;
	private static String rightFormula7;
	private static String rightFormula8;
	private static String rightFormula9;
	private static String badFormula1;
	private static String badFormula2;
	private static String badFormula3;
	private static String badFormula4;
	private static String badFormula5;
	
	@BeforeAll
	private static void setUp() {
		rightFormula1 = "[trigger < 2]";
		rightFormula2 = "[trigger = 4] OR [P.age < 30]";
		rightFormula3 = "[trigger = 4] AND [P.age < 30]";
		rightFormula4 = "[trigger = 3] AND [P.age < 30] AND [P.sex = F]";
		rightFormula5 = "[trigger = 3] OR [P.age < 30] OR [P.sex = M]";
		rightFormula6 = "[trigger = 3] OR [P.age < 30] AND [P.sex = M]";
		rightFormula7 = "[trigger = 3] AND [P.age < 30] OR [P.sex = M]";
		rightFormula8 = "[trigger >= 5] AND [trigger <= 6] AND [P.age < 30] AND [P.sex = F]";
		rightFormula9 = "[trigger >= 5] AND [trigger <= 6] OR [P.age < 30] AND [P.sex = F]";
		
		badFormula1 = "(trigger = 8)";
		badFormula2 = "[trigger = 8";
		badFormula3 = "trigger = 8 AND P.age < 30";
		badFormula4 = "[trigger = 8] XOR [P.age < 30]";
		badFormula5 = "[trigger >= 2] AND [trigger <= 5] OR [P.age >= 30] OR";
	}

	@Test
	void validateReturnsTrueWhenFormulaOK() {
		assertTrue(formulaValidator.validate(rightFormula1));
		assertTrue(formulaValidator.validate(rightFormula2));
		assertTrue(formulaValidator.validate(rightFormula3));
		assertTrue(formulaValidator.validate(rightFormula4));
		assertTrue(formulaValidator.validate(rightFormula5));
		assertTrue(formulaValidator.validate(rightFormula6));
		assertTrue(formulaValidator.validate(rightFormula7));
		assertTrue(formulaValidator.validate(rightFormula8));
		assertTrue(formulaValidator.validate(rightFormula9));
	}

	@Test
	void validateReturnsFalseWhenFormulaNOK() {
		assertFalse(formulaValidator.validate(badFormula1));
		assertFalse(formulaValidator.validate(badFormula2));
		assertFalse(formulaValidator.validate(badFormula3));
		assertFalse(formulaValidator.validate(badFormula4));
		assertFalse(formulaValidator.validate(badFormula5));
	}

}
