package com.tipikae.patientservice.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.tipikae.patientservice.dto.NewPatientDTO;

class ValidGenderTest {

	private static Validator validator;

    @BeforeAll
    public static void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory()
            .getValidator();
    }

	@Test
	void valid() {
		char sex = 'M';
		NewPatientDTO newPatientDTO = new NewPatientDTO("a", "b", LocalDate.of(2000, 01, 01), sex, "c", "d");
		
		Set<ConstraintViolation<NewPatientDTO>> violations = validator.validate(newPatientDTO); 
	    assertThat(violations).isEmpty(); 
	}

	@Test
	void invalid() {
		char sex = 'm';
		NewPatientDTO newPatientDTO = new NewPatientDTO("a", "b", LocalDate.of(2000, 01, 01), sex, "c", "d");
		
		Set<ConstraintViolation<NewPatientDTO>> violations = validator.validate(newPatientDTO); 
	    assertTrue(violations.size() > 0); 
	}
}
