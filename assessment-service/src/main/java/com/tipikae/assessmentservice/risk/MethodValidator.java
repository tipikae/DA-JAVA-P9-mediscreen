/**
 * 
 */
package com.tipikae.assessmentservice.risk;

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

/**
 * Validator with method.
 * @author tipikae
 * @version 1.0
 *
 */
public class MethodValidator extends AbstractValidator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MethodValidator.class);
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
			String method = elements.get(0);
			String operand = elements.get(1);
			String value = elements.get(2);
			
			if (method.equals(TRIGGER)) {
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
