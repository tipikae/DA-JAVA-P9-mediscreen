/**
 * 
 */
package com.tipikae.assessmentservice.assessment;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tipikae.assessmentservice.model.Note;

/**
 * Assessment health risks.
 * @author tipikae
 * @version 1.0
 *
 */
public class AssessmentImpl implements IAssessment {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AssessmentImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String assessDiabetes(int age, char sex, List<Note> notes) {
		// TODO Auto-generated method stub
		return null;
	}

}
