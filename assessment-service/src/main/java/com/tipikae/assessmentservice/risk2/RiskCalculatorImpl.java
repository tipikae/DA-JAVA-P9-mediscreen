/**
 * 
 */
package com.tipikae.assessmentservice.risk2;

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
	private IEvaluator evaluator;
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String calculateRisk(Patient patient) throws RiskNotFoundException {
		LOGGER.debug("calculateRisk: patientId=" + patient.getId());
		List<Formula> formulas = formulaRepository.findAll();
		if(formulas.isEmpty()) {
			LOGGER.debug("calculateRisk: no formulas found.");
			throw new RiskNotFoundException("No formulas found.");
		}
		
		for(Formula formula: formulas) {
			if(!formulaValidator.validate(formula.getForm())) {
				LOGGER.debug("calculateRisk: formula=" + formula + " is not valid.");
				continue;
			}
			
			List<String> operations = formulaParser.getOperations(formula.getForm());
			List<String> operators = formulaParser.getOperators(formula.getForm());
			
		}
		 
		return null;
	}

}
