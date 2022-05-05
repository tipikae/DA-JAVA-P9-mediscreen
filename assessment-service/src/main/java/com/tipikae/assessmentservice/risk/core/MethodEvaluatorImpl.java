/**
 * 
 */
package com.tipikae.assessmentservice.risk.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tipikae.assessmentservice.exception.ExpressionValidationException;
import com.tipikae.assessmentservice.exception.ValidatorNotFoundException;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.risk.parser.IExpressionParser;
import com.tipikae.assessmentservice.risk.validator.IMethodValidator;

/**
 * Method evaluator of an expression.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class MethodEvaluatorImpl implements IMethodEvaluator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodEvaluatorImpl.class);
	
	@Autowired
	private IExpressionParser expressionParser;
	
	@Autowired
	private IMethodValidator methodTriggerValidator;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean evaluateExpression(Object obj, String expression) 
			throws ValidatorNotFoundException, ExpressionValidationException {
		LOGGER.debug("evaluateExpression: expression=" + expression);
		List<String> elements = expressionParser.getMethodElements(expression);
		if (!elements.isEmpty() && elements.size() == 3) {
			String methodName = elements.get(0);
			String operand = elements.get(1);
			String expected = elements.get(2);
			
			try {
				Method method = this.getClass()
						.getDeclaredMethod(methodName,Object.class, String.class, String.class);
				method.setAccessible(true);
				return  (boolean) method.invoke(this, obj, operand, expected);
			} catch (Exception e) {
				LOGGER.debug("evaluateExpression: exception=" + e.getClass().getSimpleName() 
						+ ", " + e.getMessage());
				throw new ValidatorNotFoundException(e.getMessage());
			}
		}
		
		LOGGER.debug("evaluateExpression: error: expression=" + expression);
		throw new ExpressionValidationException("Expression errror: expression=" + expression);
	}
	
	private boolean trigger(Object obj, String operand, String expected) 
			throws ExpressionValidationException {
		LOGGER.debug("trigger: operand=" + operand + ", expected=" + expected);
		if(!(obj instanceof Patient)) {
			LOGGER.debug("trigger: bad object class=" + obj.getClass().getSimpleName());
			throw new ExpressionValidationException("Bad object class=" + obj.getClass().getSimpleName());
		}
		methodTriggerValidator.setObject(obj);
		return methodTriggerValidator.valid(operand, expected);
	}

}
