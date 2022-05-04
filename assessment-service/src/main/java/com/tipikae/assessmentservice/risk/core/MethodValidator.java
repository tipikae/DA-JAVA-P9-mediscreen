/**
 * 
 */
package com.tipikae.assessmentservice.risk.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tipikae.assessmentservice.exception.ExpressionValidationException;
import com.tipikae.assessmentservice.exception.ValidatorNotFoundException;
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
	
	private IExpressionParser expressionParser = new ExpressionParserImpl();
	
	private Object object;

	public MethodValidator(String expression, Object object) {
		super(expression);
		this.object = object;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean valid() throws ExpressionValidationException, ValidatorNotFoundException {
		LOGGER.debug("valid: method expression=" + expression);
		List<String> elements = expressionParser.getMethodElements(expression);
		
		if (!elements.isEmpty() && elements.size() == 3) {
			String methodName = elements.get(0);
			String operand = elements.get(1);
			String value = elements.get(2);
			
			String upperMethodName = methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
			String className = PACKAGE + "." + CLASS_PREFIX + upperMethodName + CLASS_SUFFIX;
			
			try {
				// invokes the method of the right class instance and calls valid()
				Class<?> classValidator = Class.forName(className);
				Constructor<?> constructorValidator = classValidator.getConstructor();
				IMethodValidator validator = (IMethodValidator) constructorValidator.newInstance();
				
				Method method = classValidator.getMethod(methodName, Object.class);
				method.invoke(validator, object);
				
				validator.valid(operand, value);
			} catch (ExpressionValidationException e) {
				LOGGER.debug("valid: ExpressionValidationException=" + e.getMessage());
				throw new ExpressionValidationException(e.getMessage());
			} catch (Exception e) {
				LOGGER.debug("valid: exception=" + e.getClass().getSimpleName() + ", " + e.getMessage());
				throw new ValidatorNotFoundException(e.getMessage());
			}
		}
		
		LOGGER.debug("valid: Method not found: expression=" + expression);
		throw new ExpressionValidationException("Method not found: expression=" + expression);
	}

}
