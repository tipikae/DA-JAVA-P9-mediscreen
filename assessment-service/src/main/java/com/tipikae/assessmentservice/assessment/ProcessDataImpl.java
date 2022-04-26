/**
 * 
 */
package com.tipikae.assessmentservice.assessment;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String calculate(int age, char sex, List<Note> notes) {
		// TODO Auto-generated method stub
		return null;
	}

}
