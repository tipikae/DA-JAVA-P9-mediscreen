/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tipikae.assessmentservice.exception.AssessmentServiceException;
import com.tipikae.assessmentservice.exception.ExpressionValidationException;
import com.tipikae.assessmentservice.exception.OperandNotFoundException;
import com.tipikae.assessmentservice.exception.RiskNotFoundException;
import com.tipikae.assessmentservice.exception.ValidatorNotFoundException;
import com.tipikae.assessmentservice.model.Formula;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.model.Risk;
import com.tipikae.assessmentservice.repository.IFormulaRepository;
import com.tipikae.assessmentservice.repository.IRiskRepository;
import com.tipikae.assessmentservice.risk.comparator.IComparator;
import com.tipikae.assessmentservice.risk.core.IEvaluator;
import com.tipikae.assessmentservice.risk.parser.IFormulaParser;

/**
 * Risk calculator.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class RiskCalculatorImpl implements IRiskCalculator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RiskCalculatorImpl.class);
	
	@Autowired
	private IRiskRepository riskRepository;
	
	@Autowired
	private IFormulaRepository formulaRepository;
	
	@Autowired
	private IFormulaParser formulaParser;
	
	@Autowired
	private IEvaluator evaluator;
	
	@Autowired
	private IComparator comparator;
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Risk calculateRisk(Patient patient) throws RiskNotFoundException {
		LOGGER.debug("calculateRisk: patientId=" + patient.getId());
		AtomicInteger countValidFormulas = new AtomicInteger(0);
		AtomicLong riskId = new AtomicLong(0);
		
		// get all formulas
		List<Formula> formulas = formulaRepository.findAll();
		formulas.forEach(formula -> {
			// parse formula -> expressions and operands
			List<String> expressions = formulaParser.getExpressions(formula.getForm());
			List<String> operands = formulaParser.getOperands(formula.getForm());
			
			// check expressions size = operands size + 1
			// if not -> new iteration
			if(expressions.size() != (operands.size() + 1)) {
				LOGGER.debug("calculateRisk: expressions size=" + expressions.size() 
					+ " != operands size=" + operands.size() + " + 1, formula=" + formula);
				return;
			}
			
			List<Boolean> results;
			try {
				List<Boolean> expressionsEvaluated = evaluateExpressions(patient, expressions);
				results = evaluateOperands(operands, expressionsEvaluated);
			} catch (Exception e) {
				LOGGER.debug("calculateRisk: error=" + e.getMessage() + ", formula=" + formula);
				return;
			}
			
			// if all results are true -> formula is valid and set riskId
			if(!results.stream().anyMatch(result -> result == false)) {
				LOGGER.debug("calculateRisk: formula=" + formula.getForm() + " is valid");
				countValidFormulas.incrementAndGet();
				riskId.set(formula.getRisk_id());
			}
		});
		
		// only one formula must be true
		int count = countValidFormulas.get();
		if(count == 0) {
			LOGGER.debug("calculateRisk: no formula found");
			throw new RiskNotFoundException("no formula found");
		} else if(count > 1) {
			LOGGER.debug("calculateRisk: multiple formulas found");
			throw new RiskNotFoundException("multiple formulas found");
		}
		
		// return risk by id
		Optional<Risk> optional = riskRepository.findById(riskId.get());
		if(optional.isEmpty()) {
			LOGGER.debug("calculateRisk: risk with id=" + riskId.get() + " not found");
			throw new RiskNotFoundException("risk with id=" + riskId.get() + " found");
		}
		
		return optional.get();
	}
	
	/**
	 * Evaluate an expressions list.
	 * @param patient Patient
	 * @param expressions List
	 * @return List
	 * @throws AssessmentServiceException 
	 */
	private List<Boolean> evaluateExpressions(Patient patient, List<String> expressions) 
			throws AssessmentServiceException {
		LOGGER.debug("evaluateExpression");
		List<Boolean> expressionsEvaluated = new ArrayList<>();
		AtomicInteger error = new AtomicInteger(0);
		
		expressions.forEach(expression -> {
			try {
				expressionsEvaluated.add(evaluator.evaluateExpression(patient, expression));
			} catch (ValidatorNotFoundException e) {
				LOGGER.debug("evaluateExpressions: validator not found: " + e.getMessage());
				error.incrementAndGet();
			} catch (ExpressionValidationException e) {
				LOGGER.debug("evaluateExpressions: exception occured when validating expression: " 
						+ e.getMessage());
				error.incrementAndGet();
			}
		});
		
		if(error.get() != 0) {
			throw new AssessmentServiceException("Exception occured when evaluating expressions");
		}
		
		return expressionsEvaluated;
	}
	
	/**
	 * Evaluate an operands list.
	 * @param operands List
	 * @param expressionsEvaluated List
	 * @return List
	 * @throws AssessmentServiceException 
	 */
	private List<Boolean> evaluateOperands(List<String> operands, List<Boolean> expressionsEvaluated) 
			throws AssessmentServiceException {
		LOGGER.debug("evaluateOperands");
		List<Boolean> results = new ArrayList<>();
		
		if(operands.isEmpty()) {
			results.add(expressionsEvaluated.get(0));
		} else {
			for(int i = 0; i < operands.size(); i++) {
				String operand = operands.get(i);
				boolean left = expressionsEvaluated.get(i);
				boolean right = expressionsEvaluated.get(i + 1);
				try {
					results.add(comparator.compareBoolean(operand, left, right));
				} catch (OperandNotFoundException e) {
					LOGGER.debug("calculateRisk: operand " + operand + " not found");
					throw new AssessmentServiceException(e.getMessage());
				}
			}
		}
		
		return results;
	}

}
