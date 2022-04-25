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

	HEMOGLOBINEA1C ("Hémoglobine A1C"),
	MICROALBUMINE ("Microalbumine"),
	TAILLE ("Taille"),
	POIDS ("Poids"),
	FUMEUR ("Fumeur"),
	ANORMAL ("Anormal"),
	CHOLESTEROL ("Cholestérol"),
	VERTIGE ("Vertige"),
	RECHUTE ("Rechute"),
	REACTION ("Réaction"),
	ANTICORPS ("Anticorps");
	
	@SuppressWarnings("unused")
	private String label;
	
	Term(String label) {
		this.label = label;
	}
}
