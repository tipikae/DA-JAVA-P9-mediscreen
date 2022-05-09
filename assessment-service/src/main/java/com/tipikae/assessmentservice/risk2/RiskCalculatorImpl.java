/**
 * 
 */
package com.tipikae.assessmentservice.risk2;

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
		for(Formula formula: formulas) {
			if(!formulaValidator.validate(formula.getForm())) {
				LOGGER.debug("calculateRisk: formula=" + formula + " is not valid.");
				continue;
			}
			
			List<String> operations = formulaParser.getOperations(formula.getForm());
			List<String> operators = formulaParser.getOperators(formula.getForm());
			if(operations.size() != (operators.size() + 1)) {
				LOGGER.debug("calculateRisk: operations size=" + operations.size() 
					+ " != operators size=" + operators.size() + " + 1");
				continue;
			}
			
			List<Boolean> results = new ArrayList<>();
			for(String operation: operations) {
				IEvaluator evaluator = evaluatorFactory.getEvaluator(operation);
				boolean result = false;
				
				try {
					result = evaluator.evaluate(patient, operation);
				} catch (Exception e) {
					LOGGER.debug("calculateRisk: evaluator error: " + e.getClass().getSimpleName() 
							+ ": " + e.getMessage());
					break;
				}
				
				if(!result) {
					LOGGER.debug("calculateRisk: evaluator returns false for operation=" + operation);
					break;
				}
				results.add(result);
			}
			
			if(results.isEmpty()) {
				LOGGER.debug("calculateRisk: no operation evaluated with formula=" + formula.getForm());
				continue;
			}
			
			
		}
		
		LOGGER.debug("calculateRisk: no valid formula found");
		throw new RiskNotFoundException("No valid formula found.");
	}
	
	private boolean evaluateResults(List<Boolean> results, List<Boolean> operators) {
		return false;
	}

}
