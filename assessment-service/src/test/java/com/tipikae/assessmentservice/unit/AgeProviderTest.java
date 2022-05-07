package com.tipikae.assessmentservice.unit;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.tipikae.assessmentservice.service.AgeProvider;

class AgeProviderTest {
	
	private AgeProvider ageProvider = new AgeProvider();

	@Test
	void calculateAge() {
		LocalDate dob = LocalDate.of(2000, 01, 01);
		assertEquals(22, ageProvider.calculateAge(dob));
	}

}
