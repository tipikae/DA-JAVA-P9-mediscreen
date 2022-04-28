package com.tipikae.assessmentservice.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tipikae.assessmentservice.assessment.ITermReader;
import com.tipikae.assessmentservice.assessment.TermReaderImpl;

class TermsReaderTest {
	
	private ITermReader termsReader = new TermReaderImpl();

	@Test
	void test() {
		assertTrue(termsReader.read().size() > 0);
	}

}
