/**
 * 
 */
package com.tipikae.assessmentservice.validation;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validator for ValidGender annotation.
 * @author tipikae
 * @version 1.0
 *
 */
public class GenderValidator implements ConstraintValidator<ValidGender, Character> {
	
	private List<String> acceptedValues;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize(ValidGender annotation) {
		acceptedValues = Stream.of(Gender.class.getEnumConstants())
				.map(Enum::name)
				.collect(Collectors.toList());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValid(Character value, ConstraintValidatorContext context) {
		if (value == null) {
            return false;
        }
		
		return acceptedValues.contains(value.toString());
	}

}
