package com.tipikae.mediscreenUI.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.tipikae.mediscreenUI.model.Patient;

class ValidationGenderTest {
	
    private static Validator validator;

    @BeforeAll
    public static void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory()
            .getValidator();
    }

	@Test
	void valid() {
		char sex = 'M';
		Patient patient = new Patient(0, "", "", LocalDate.of(2000, 01, 01), sex, "", "");
		
		Set<ConstraintViolation<Patient>> violations = validator.validate(patient); 
	    assertThat(violations).isEmpty(); 
	}

	@Test
	void invalid() {
		char sex = 'm';
		Patient patient = new Patient(0, "", "", LocalDate.of(2000, 01, 01), sex, "", "");
		
		Set<ConstraintViolation<Patient>> violations = validator.validate(patient); 
	    assertTrue(violations.size() > 0); 
	}

}
