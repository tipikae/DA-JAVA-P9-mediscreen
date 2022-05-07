/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tipikae.assessmentservice.exception.RiskNotFoundException;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.model.Risk;
import com.tipikae.assessmentservice.repository.IFormulaRepository;
import com.tipikae.assessmentservice.repository.IRiskRepository;
import com.tipikae.assessmentservice.risk.comparator.IComparator;
import com.tipikae.assessmentservice.risk.core.IEvaluator;
import com.tipikae.assessmentservice.risk.parser.IFormulaParser;

/**
 * 
 * Risk calculator.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class RiskCalculatorImpl2 implements IRiskCalculator {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RiskCalculatorImpl2.class);
	
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
		// TODO Auto-generated method stub
		return null;
	}

}
