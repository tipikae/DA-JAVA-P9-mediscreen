/**
 * 
 */
package com.tipikae.assessmentservice.risk.term;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tipikae.assessmentservice.repository.ITriggerRepository;

/**
 * Terms reader.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class TermReaderImpl implements ITermReader {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TermReaderImpl.class);
	
	@Autowired
	private ITriggerRepository triggerRepository;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<String> getTerms() {
		LOGGER.debug("getTerms from repository");
		return triggerRepository.findAll().stream()
				.map(trigger -> trigger.getTerm())
				.collect(Collectors.toList());
	}

}
