package com.tipikae.assessmentservice.unit;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.tipikae.assessmentservice.assessment.IProcessData;
import com.tipikae.assessmentservice.assessment.ProcessDataImpl;

class ProcessDataTest {
	
	private IProcessData processData = new ProcessDataImpl();
	
	private static char sexM = 'M';
	private static char sexF = 'F';
	private static int age29 = 29;
	private static int age30 = 30;
	
	
	@BeforeAll
	private static void setUp() {
		
	}

	@Test
	void calculateReturnsNoneWhenNoTerm() {
		
	}

	@Test
	void calculateReturnsBorderlineWhen2TermsAndMore30() {
		
	}

	@Test
	void calculateReturnsInDangerWhenLess30AndManAnd3TermsOrWomanAnd4TermsOrMore30And6Terms() {
		
	}

	@Test
	void calculateReturnsEarlyOnsetWhenLess30AndManAnd5TermsOrWomanAnd7TermsOrMore30And8Terms() {
		
	}

}
