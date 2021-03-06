package com.tipikae.assessmentservice.unit;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.risk.IViewResult;
import com.tipikae.assessmentservice.risk.ViewResultImpl;

class ViewResultTest {
	
	private IViewResult viewResult = new ViewResultImpl();
	
	private static String family = "family";
	private static String given = "given";
	private static int age = 22;
	private static String result = "None";
	private static Patient patient = new Patient(1, family, given, LocalDate.of(2000, 01, 01), 'M', "", "");

	@Test
	void getResultView() {
		String expected = "Patient: " + patient.getGiven() + " " + patient.getFamily() 
		+ " (age " + age + ") diabetes assessment is: " + result;
		assertEquals(expected, viewResult.getResultView(patient, result)); 
	}

	@Test
	void getErrorView() {
		String expected = patient.getGiven() + ": service unavailable.";
		assertEquals(expected, viewResult.getErrorView(patient, "error"));
	}

}
