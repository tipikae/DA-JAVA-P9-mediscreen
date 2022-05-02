/**
 * 
 */
package com.tipikae.assessmentservice.risk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tipikae.assessmentservice.model.Risk;
import com.tipikae.assessmentservice.repository.IFormulaRepository;
import com.tipikae.assessmentservice.repository.IRiskRepository;
import com.tipikae.assessmentservice.service.RiskNotFoundException;

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
	

	@Override
	public Risk calculateRisk(int count, int age, char sex) throws RiskNotFoundException {
		// get formules
		// loop: parse formule
		//		loop evaluate expression
		// 		loop operande
		// return risk
		return null;
	}

}
