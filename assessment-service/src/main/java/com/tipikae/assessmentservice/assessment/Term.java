/**
 * 
 */
package com.tipikae.assessmentservice.assessment;

/**
 * Trigger terms.
 * @author tipikae
 * @version 1.0
 *
 */
public enum Term {

	HEMOGLOBINEA1C ("Hemoglobin A1C"),// ("Hémoglobine A1C"),
	MICROALBUMINE ("Microalbumin"),// ("Microalbumine"),
	TAILLE ("Height"),// ("Taille"),
	POIDS  ("Weight"),// ("Poids"),
	FUMEUR  ("Smoker"),// ("Fumeur"),
	ANORMAL  ("Abnormal"),// ("Anormal"),
	CHOLESTEROL  ("Cholesterol"),// ("Cholestérol"),
	VERTIGE  ("Dizziness"),// ("Vertige"),
	RECHUTE  ("Relapse"),// ("Rechute"),
	REACTION  ("Reaction"),// ("Réaction"),
	ANTICORPS  ("Antibodies");// ("Anticorps");
	
	private String label;
	
	Term(String label) {
		this.label = label;
	}
	
	public String getLabel() {
		return label;
	}
}
