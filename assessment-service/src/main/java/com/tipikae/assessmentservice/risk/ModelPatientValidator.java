/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tipikae.assessmentservice.exception.ExpressionValidationException;
import com.tipikae.assessmentservice.exception.OperandNotFoundException;
import com.tipikae.assessmentservice.risk.comparator.ComparatorImpl;
import com.tipikae.assessmentservice.risk.comparator.IComparator;
import com.tipikae.assessmentservice.risk.parser.ExpressionParserImpl;
import com.tipikae.assessmentservice.risk.parser.IExpressionParser;

/**
 * Validator with model object.
 * @author tipikae
 * @version 1.0
 *
 */
public class ModelPatientValidator extends AbstractValidator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ModelPatientValidator.class);
	private static final String AGE = "age";
	private static final String SEX = "sex";
	
	//@Autowired
	private IExpressionParser expressionParser = new ExpressionParserImpl();
	
	@Autowired
	private IComparator comparator = new ComparatorImpl();
	
	private int age;
	private char sex;

	public ModelPatientValidator(String expression, int age, char sex) {
		super(expression);
		this.age = age;
		this.sex = sex;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean valid() throws ExpressionValidationException {
		LOGGER.debug("valid: expression=" + expression + ", age=" + age + ", sex=" + sex);
		List<String> elements = expressionParser.getModelElements('P', expression);
		
		if (!elements.isEmpty()) {
			if (elements.get(0).equals(AGE)) {
				try {
					return comparator.compareInt(elements.get(1), age, Integer.parseInt(elements.get(2)));
				} catch (NumberFormatException | OperandNotFoundException e) {
					throw new ExpressionValidationException(e.getMessage());
				}
			} else if (elements.get(0).equals(SEX)) {
				try {
					return comparator.compareCharacter(elements.get(1), sex, elements.get(2).charAt(0));
				} catch (OperandNotFoundException e) {
					throw new ExpressionValidationException(e.getMessage());
				}
			} 
		}
		throw new ExpressionValidationException("Field not found.");
	}

}
