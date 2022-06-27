/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import java.util.function.BinaryOperator;

/**
 * Boolean operator.
 * @author tipikae
 * @version 1.0
 *
 */
public enum BooleanOperator implements BinaryOperator<Boolean> {

	/**
	 * And operator.
	 */
	AND ("AND") {
		@Override
		public Boolean apply(Boolean a, Boolean b) {
			return a && b;
		}
	},
	
	/**
	 * Or operator.
	 */
	OR ("OR") {
		@Override
		public Boolean apply(Boolean a, Boolean b) {
			return a || b;
		}
	};
	
	private String operator;
	
	private BooleanOperator(String operator) {
		this.operator = operator;
	}
	
	/**
	 * Get a value of an operator.
	 * @param operator
	 * @return BooleanOperator
	 */
	public static BooleanOperator valueOfOperator(String operator) {
	    for (BooleanOperator o : values()) {
	        if (o.operator.equals(operator)) {
	            return o;
	        }
	    }
	    return null;
	}
	 
}
