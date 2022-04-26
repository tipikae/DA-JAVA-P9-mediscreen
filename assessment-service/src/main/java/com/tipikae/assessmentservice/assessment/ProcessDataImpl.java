/**
 * 
 */
package com.tipikae.assessmentservice.assessment;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tipikae.assessmentservice.model.Note;

/**
 * Process data for assessment.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class ProcessDataImpl implements IProcessData {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessDataImpl.class);
	private static final int LIMIT = 30;
	
	@Autowired
	private ITermCounter termCounter;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getRisk(int age, Gender gender, List<Note> notes) {
		int counter = termCounter.countTerms(notes);
		LOGGER.debug("getRisk: age=" + age + ", sex=" + gender + ", terms=" + counter);
		Risk risk;
		
		// terms counter in notes
		switch(counter) {
			case 0:
				risk = Risk.NONE;
				break;
			case 1:
				risk = Risk.NONE;
				break;
			case 2:
				if(age < LIMIT) {
					risk = Risk.NONE;
				} else {
					risk = Risk.BORDERLINE;
				}
				break;
			case 3:
				if(age < LIMIT) {
					if(gender == Gender.MALE) {
						risk = Risk.INDANGER;
					} else {
						risk = Risk.NONE;
					}
				} else {
					risk = Risk.BORDERLINE;
				}
				break;
			case 4:
				if(age < LIMIT) {
					risk = Risk.INDANGER;
				} else {
					risk = Risk.BORDERLINE;
				}
				break;
			case 5:
				if(age < LIMIT) {
					if(gender == Gender.MALE) {
						risk = Risk.EARLYONSET;
					} else {
						risk = Risk.INDANGER;
					}
				} else {
					risk = Risk.BORDERLINE;
				}
				break;
			case 6:
				if(age < LIMIT) {
					if(gender == Gender.MALE) {
						risk = Risk.EARLYONSET;
					} else {
						risk = Risk.INDANGER;
					}
				} else {
					risk = Risk.INDANGER;
				}
				break;
			case 7:
				if(age < LIMIT) {
					risk = Risk.EARLYONSET;
				} else {
					risk = Risk.INDANGER;
				}
				break;
			default:
				// 8 and more terms
				risk = Risk.EARLYONSET;
		}
		
		return risk.getLabel();
	}

}
