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

	/**
	 * Less than operator.
	 */
	LESS_THAN ("<") {
		@Override
		public Boolean apply(Integer a, Integer b) {
			return a.compareTo(b) < 0;
		}
	},
	
	/**
	 * Less then or equals operator.
	 */
	LESS_THAN_OR_EQUALS ("<=") {
		@Override
		public Boolean apply(Integer a, Integer b) {
			return a.compareTo(b) <= 0;
		}
	},
	
	/**
	 * Greater than operator.
	 */
	GREATER_THAN (">") {
		@Override
		public Boolean apply(Integer a, Integer b) {
			return a.compareTo(b) > 0;
		}
	},
	
	/**
	 * Greater than or equals operator.
	 */
	GREATER_THAN_OR_EQUALS (">=") {
		@Override
		public Boolean apply(Integer a, Integer b) {
			return a.compareTo(b) >= 0;
		}
	},
	
	/**
	 * Equals operator.
	 */
	EQUALS ("=") {
		@Override
		public Boolean apply(Integer a, Integer b) {
			return a.compareTo(b) == 0;
		}
	},
	
	/**
	 * Differents operator.
	 */
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
	
	/**
	 * Get a value of an operator.
	 * @param operator
	 * @return ArithmeticOperator
	 */
	public static ArithmeticOperator valueOfOperator(String operator) {
	    for (ArithmeticOperator o : values()) {
	        if (o.operator.equals(operator)) {
	            return o;
	        }
	    }
	    return null;
	}

}
