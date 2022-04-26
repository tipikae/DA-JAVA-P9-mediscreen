/**
 * 
 */
package com.tipikae.assessmentservice.assessment;

/**
 * Risk level.
 * @author tipikae
 * @version 1.0
 *
 */
public enum Risk {

	NONE ("None"),
	BORDERLINE ("Borderline"),
	INDANGER ("In Danger"),
	EARLYONSET ("Early onset");
	
	@SuppressWarnings("unused")
	private final String label;
	
	Risk(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
