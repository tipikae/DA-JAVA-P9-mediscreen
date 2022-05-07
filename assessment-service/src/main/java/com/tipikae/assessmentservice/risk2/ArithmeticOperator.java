/**
 * 
 */
package com.tipikae.assessmentservice.risk2;

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
			return a < b;
		}
	},
	LESS_THAN_OR_EQUALS ("<=") {
		@Override
		public Boolean apply(Integer a, Integer b) {
			return a <= b;
		}
	},
	GREATER_THAN (">") {
		@Override
		public Boolean apply(Integer a, Integer b) {
			return a > b;
		}
	},
	GREATER_THAN_OR_EQUALS (">=") {
		@Override
		public Boolean apply(Integer a, Integer b) {
			return a >= b;
		}
	},
	EQUALS ("=") {
		@Override
		public Boolean apply(Integer a, Integer b) {
			return a == b;
		}
	},
	DIFFERENTS ("!=") {
		@Override
		public Boolean apply(Integer a, Integer b) {
			return a != b;
		}
	};
	
	@SuppressWarnings("unused")
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
