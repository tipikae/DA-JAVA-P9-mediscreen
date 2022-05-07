package com.tipikae.assessmentservice.unit;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.tipikae.assessmentservice.util.IUtil;
import com.tipikae.assessmentservice.util.UtilImpl;

class UtilTest {
	
	private IUtil util = new UtilImpl();

	@Test
	void calculateAge() {
		LocalDate dob = LocalDate.of(2000, 01, 01);
		assertEquals(22, util.calculateAge(dob));
	}

}
