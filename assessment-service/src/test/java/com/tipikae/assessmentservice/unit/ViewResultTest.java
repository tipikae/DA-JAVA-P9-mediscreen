package com.tipikae.assessmentservice.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tipikae.assessmentservice.assessment.IViewResult;
import com.tipikae.assessmentservice.assessment.ViewResultImpl;
import com.tipikae.assessmentservice.model.Patient;

class ViewResultTest {
	
	private IViewResult viewResult = new ViewResultImpl();
	
	private static String family = "family";
	private static String given = "given";
	private static int age = 20;
	private static String result = "None";
	private static Patient patient = new Patient(1, family, given, null, 'M', "", "");

	@Test
	void getResultView() {
		String expected = "Patient: " + patient.getGiven() + " " + patient.getFamily() 
		+ " (age " + age + ") diabetes assessment is: " + result;
		assertEquals(expected, viewResult.getResultView(patient, age, result)); 
	}

	@Test
	void getErrorView() {
		String expected = patient.getGiven() + ": no result, error getting notes.";
		assertEquals(expected, viewResult.getErrorView(patient, "error"));
	}

}
