package com.tipikae.assessmentservice;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.assessmentservice.exception.NotFoundException;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.risk.IRiskCalculator;

@SpringBootTest
class RiskcalculatorIT {
	
	@Autowired
	private IRiskCalculator riskCalculator;
	
	private static Patient patientNoneMore30F;
	private static Patient patientBorderlineMore30M;
	private static Patient patientInDangerLess30M;
	private static Patient patientEarlyOnsetLess30F;
	private static Patient patientNotFound;
	
	@BeforeAll
	private static void setUp() {
		patientNoneMore30F = new Patient(1L, "", "", LocalDate.of(1966, 12, 31), 'F', "", "");
		patientBorderlineMore30M = new Patient(2L, "", "", LocalDate.of(1945, 06, 24), 'M', "", "");
		patientInDangerLess30M = new Patient(3L, "", "", LocalDate.of(2004, 06, 18), 'M', "", "");
		patientEarlyOnsetLess30F = new Patient(4L, "", "", LocalDate.of(2002, 06, 28), 'F', "", "");
		patientNotFound = new Patient(10L, "", "", LocalDate.of(2000, 01, 01), 'F', "", "");
	}

	@Test
	void calculateRiskReturnsRiskWhenOk() throws NotFoundException {
		assertEquals("None", riskCalculator.calculateRisk(patientNoneMore30F));
		assertEquals("Borderline", riskCalculator.calculateRisk(patientBorderlineMore30M));
		assertEquals("In Danger", riskCalculator.calculateRisk(patientInDangerLess30M));
		assertEquals("Early onset", riskCalculator.calculateRisk(patientEarlyOnsetLess30F));
	}
	
	@Test
	void calculateRiskReturnsNoneWhenPatientNotFound() throws NotFoundException {
		assertEquals("None", riskCalculator.calculateRisk(patientNotFound));
	}

}
