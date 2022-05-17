/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import java.util.function.BiFunction;

/**
 * Arithmetic operator.
 * @author tipikae
 * @version 1.0
 *
 */
public enum ArithmeticOperator implements BiFunction<Integer, Integer, Boolean> {

	LESS_THAN ("<") {
		@Override
		public Boolean apply(Integer a, Integer b) {
			return a.compareTo(b) < 0;
		}
	},
	LESS_THAN_OR_EQUALS ("<=") {
		@Override
		public Boolean apply(Integer a, Integer b) {
			return a.compareTo(b) <= 0;
		}
	},
	GREATER_THAN (">") {
		@Override
		public Boolean apply(Integer a, Integer b) {
			return a.compareTo(b) > 0;
		}
	},
	GREATER_THAN_OR_EQUALS (">=") {
		@Override
		public Boolean apply(Integer a, Integer b) {
			return a.compareTo(b) >= 0;
		}
	},
	EQUALS ("=") {
		@Override
		public Boolean apply(Integer a, Integer b) {
			return a.compareTo(b) == 0;
		}
	},
	DIFFERENTS ("!=") {
		@Override
		public Boolean apply(Integer a, Integer b) {
			return a.compareTo(b) != 0;
		}
	};
	
	private String operator;
	
	private ArithmeticOperator(String operator) {
		this.operator = operator;
	}
	
	public static ArithmeticOperator valueOfOperator(String operator) {
	    for (ArithmeticOperator o : values()) {
	        if (o.operator.equals(operator)) {
	            return o;
	        }
	    }
	    return null;
	}

}
