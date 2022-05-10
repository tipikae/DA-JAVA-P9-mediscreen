/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tipikae.assessmentservice.exception.RiskNotFoundException;
import com.tipikae.assessmentservice.model.Formula;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.repository.IFormulaRepository;

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
	private IFormulaRepository formulaRepository;
	
	@Autowired
	private FormulaValidator formulaValidator;
	
	@Autowired
	private FormulaParser formulaParser;
	
	@Autowired
	private EvaluatorFactory evaluatorFactory;
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String calculateRisk(Patient patient) throws RiskNotFoundException {
		LOGGER.debug("calculateRisk: patientId=" + patient.getId());
		
		// get formulas list
		List<Formula> formulas = formulaRepository.findAll();
		if(formulas.isEmpty()) {
			LOGGER.debug("calculateRisk: no formulas found.");
			throw new RiskNotFoundException("No formulas found.");
		}
		
		// search first formula valid and evaluated
		formulas: 
		for(Formula formula: formulas) {
			if(!formulaValidator.validate(formula.getForm())) {
				LOGGER.debug("calculateRisk: formula=" + formula.getForm() + " is not valid.");
				continue formulas;
			}
			
			// parse formula in operations and boolean operators
			List<String> operations = formulaParser.getOperations(formula.getForm());
			List<String> operators = formulaParser.getOperators(formula.getForm());
			if(operations.size() != (operators.size() + 1)) {
				LOGGER.debug("calculateRisk: operations size=" + operations.size() 
					+ " != operators size=" + operators.size() + " + 1");
				continue formulas;
			}
			
			// evaluate each operation
			List<Boolean> results = new ArrayList<>();
			for(String operation: operations) {
				IEvaluator evaluator = evaluatorFactory.getEvaluator(operation);
				
				try {
					boolean result = evaluator.evaluate(patient, operation);
					LOGGER.debug("calculateRisk: operation=" + operation + " is " + result);
					results.add(result);
				} catch (Exception e) {
					LOGGER.debug("calculateRisk: evaluator error: " + e.getClass().getSimpleName() 
							+ ": " + e.getMessage());
					continue formulas;
				}
			}
			
			// evaluate final results
			LOGGER.debug("calculateRisk: final: results size=" + results.size());
			boolean eval;
			if(results.isEmpty()) {
				LOGGER.debug("calculateRisk: final: no operation evaluated with formula=" 
						+ formula.getForm());
				continue;
			} else if(results.size() == 1) {
				eval = results.get(0);
			} else {
				eval = true;
				for(int i = 0; i < operators.size(); i++) {
					String operator = operators.get(i);
					boolean left = results.get(i);
					boolean right = results.get(i + 1);
					boolean temp = left;
					LOGGER.debug("calculateRisk: final: operator=" + operator + ", left=" + left 
							+ ", right=" + right);
					
					if(BooleanOperator.valueOfOperator(operator) != null) {
						temp = BooleanOperator.valueOfOperator(operator).apply(left, right);
						LOGGER.debug("calculateRisk: final: adding " + temp);
					}

					eval = eval && temp;	
				}
			}
			
			if(eval) {
				LOGGER.debug("calculateRisk: formula=" + formula.getForm() + " is evaluated");
				return formula.getRisk();
			}
		}
		
		LOGGER.debug("calculateRisk: no valid formula found");
		throw new RiskNotFoundException("No valid formula found.");
	}

}
