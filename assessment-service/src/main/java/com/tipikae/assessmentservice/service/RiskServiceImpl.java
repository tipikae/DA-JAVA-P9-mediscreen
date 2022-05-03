/**
 * 
 */
package com.tipikae.assessmentservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tipikae.assessmentservice.assessment.ITermCounter;
import com.tipikae.assessmentservice.assessment.Risk;
import com.tipikae.assessmentservice.exception.RiskNotFoundException;
import com.tipikae.assessmentservice.model.Note;
import com.tipikae.assessmentservice.risk.IRiskCalculator;

/**
 * FormulaService.
 * @author tipikae
 * @version 1.0
 *
 */
@Service
public class RiskServiceImpl implements IRiskService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RiskServiceImpl.class);
	
	@Autowired
	private ITermCounter termCounter;
	
	@Autowired
	private IRiskCalculator riskCalculator;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Risk getRisk(int age, char sex, List<Note> notes) throws RiskNotFoundException {
		// count term
		// calculate risk
		return null;
	}

}
