package com.tipikae.assessmentservice.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tipikae.assessmentservice.assessment.TermReaderImpl;
import com.tipikae.assessmentservice.exception.TermReaderException;
import com.tipikae.assessmentservice.properties.TermsProperties;

@ExtendWith(MockitoExtension.class)
class TermsReaderTest {
	
	@Mock
	private TermsProperties termsConfig;
	
	@InjectMocks
	private TermReaderImpl termsReader;

	@Test
	void test() throws TermReaderException {
		when(termsConfig.getPath()).thenReturn("terms");
		assertTrue(termsReader.read().size() > 0);
	}

}
