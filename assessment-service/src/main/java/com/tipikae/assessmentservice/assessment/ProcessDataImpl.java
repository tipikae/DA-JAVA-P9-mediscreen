/**
 * 
 */
package com.tipikae.assessmentservice.assessment;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tipikae.assessmentservice.model.Assessment;
import com.tipikae.assessmentservice.model.Note;
import com.tipikae.assessmentservice.model.Patient;
import com.tipikae.assessmentservice.util.IUtil;

/**
 * Process data for assessment.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class ProcessDataImpl implements IProcessData {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessDataImpl.class);
	
	@Autowired
	private IAssessment assessment;
	
	@Autowired
	private IUtil util;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Assessment calculate(Patient patient, List<Note> notes) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String formatResponse(String family, String given, int age, String result) {
		return "Patient: " + given + " " + family + " (age " + age + ") diabetes assessment is: " + result;
	}

}
