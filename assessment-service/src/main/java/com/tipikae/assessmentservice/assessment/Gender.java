/**
 * 
 */
package com.tipikae.assessmentservice.assessment;

/**
 * Gender of a patient.
 * @author tipikae
 * @version 1.0
 *
 */
public enum Gender {

	MALE ('M'),
	FEMALE ('F');
	
	private char label;
	
	Gender(char label) {
		this.label = label;
	}
	
	public char getLabel() {
		return label;
	}
}
