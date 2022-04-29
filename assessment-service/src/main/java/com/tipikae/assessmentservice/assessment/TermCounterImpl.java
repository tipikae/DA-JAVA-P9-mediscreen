/**
 * 
 */
package com.tipikae.assessmentservice.assessment;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tipikae.assessmentservice.model.Note;

/**
 * Term counter.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class TermCounterImpl implements ITermCounter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TermCounterImpl.class);
	
	@Autowired
	private ITermReader termsReader;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int countTerms(List<Note> notes) throws Exception {
		List<String> terms = termsReader.read();
		LOGGER.debug("countTerms: notes size=" + notes.size() + ", terms size=" + terms.size());
		
		return notes.parallelStream()
						.map(note -> {
							AtomicInteger count = new AtomicInteger(0);
							String message = note.getE().toLowerCase();
							terms.forEach(term -> {
								if(message.contains(term.toLowerCase())) {
									count.incrementAndGet();
								}
							});
							return count.get();
						})
						.reduce(0, Integer::sum);
	}

}
