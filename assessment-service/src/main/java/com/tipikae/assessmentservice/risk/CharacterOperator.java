/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import java.util.function.BiFunction;

/**
 * Enumeration of character operators.
 * @author tipikae
 * @version 1.0
 *
 */
public enum CharacterOperator implements BiFunction<Character, Character, Boolean> {

	EQUALS ("=") {
		@Override
		public Boolean apply(Character a, Character b) {
			return a == b;
		}
	},
	DIFFERENTS ("!=") {
		@Override
		public Boolean apply(Character a, Character b) {
			return a != b;
		}
	};

	private String operator;
	
	private CharacterOperator(String operator) {
		this.operator = operator;
	}
	
	public static CharacterOperator valueOfOperator(String operator) {
	    for (CharacterOperator o : values()) {
	        if (o.operator.equals(operator)) {
	            return o;
	        }
	    }
	    return null;
	}
}
