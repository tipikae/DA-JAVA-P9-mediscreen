/**
 * 
 */
package com.tipikae.assessmentservice.risk.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tipikae.assessmentservice.exception.ExpressionValidationException;
import com.tipikae.assessmentservice.exception.OperandNotFoundException;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.risk.comparator.ComparatorImpl;
import com.tipikae.assessmentservice.risk.comparator.IComparator;
import com.tipikae.assessmentservice.risk.parser.ExpressionParserImpl;
import com.tipikae.assessmentservice.risk.parser.IExpressionParser;
import com.tipikae.assessmentservice.risk.validator.IMethodValidator;

/**
 * Validator with method.
 * @author tipikae
 * @version 1.0
 *
 */
public class MethodValidator extends AbstractValidator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodValidator.class);
	private static final String PACKAGE = "com.tipikae.assessmentservice.risk.validator";
	private static final String CLASS_PREFIX = "Method";
	private static final String CLASS_SUFFIX = "Validator";
	private static final String TRIGGER = "trigger";
	
	private IExpressionParser expressionParser = new ExpressionParserImpl();
	private IComparator comparator = new ComparatorImpl();
	
	private Object object;

	public MethodValidator(String expression, Object object) {
		super(expression);
		this.object = object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean valid() throws ExpressionValidationException {
		LOGGER.debug("valid: method expression=" + expression);
		List<String> elements = expressionParser.getMethodElements(expression);
		
		if (!elements.isEmpty() && elements.size() == 3) {
			String methodName = elements.get(0);
			String operand = elements.get(1);
			String value = elements.get(2);
			
			String upperMethodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
			String className = CLASS_PREFIX + upperMethodName + CLASS_SUFFIX;
			try {
				Class<?> methodValidatorClass = Class.forName(className);
				Method method = methodValidatorClass.getMethod(methodName, Object.class);
				method.invoke((IMethodValidator) methodValidatorClass.newInstance(), object); 
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (methodName.equals(TRIGGER)) {
				if(object instanceof Patient) {
					Patient patient = (Patient) object;
					int count = 0;
					try {
						return comparator.compareInt(operand, count, Integer.parseInt(value));
					} catch (NumberFormatException | OperandNotFoundException e) {
						throw new ExpressionValidationException(e.getMessage());
					}
				}
			} 
		}
		
		throw new ExpressionValidationException("Method not found: expression=" + expression);
	}

}
