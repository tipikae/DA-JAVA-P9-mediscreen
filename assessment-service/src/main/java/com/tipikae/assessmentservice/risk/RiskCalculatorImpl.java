/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tipikae.assessmentservice.exception.RiskNotFoundException;
import com.tipikae.assessmentservice.model.Risk;
import com.tipikae.assessmentservice.repository.IFormulaRepository;
import com.tipikae.assessmentservice.repository.IRiskRepository;

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
	

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Risk calculateRisk(int count, int age, char sex) throws RiskNotFoundException {
		// get formules
		// loop formules: 
		//		parse formule: expressions, operandes
		//		loop expressions:
		//			evaluate expression -> boolean[]
		// 		loop operande
		//		eval boolean if true
		// only one true -> if not throw
		// return risk by id
		return null;
	}

}
