/**
 * 
 */
package com.tipikae.assessmentservice.assessment;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int countTerms(List<Note> notes) {
		LOGGER.debug("countTerms: notes size=" + notes.size());
		List<Term> terms = Arrays.asList(Term.values());
		
		return notes.parallelStream()
						.map(note -> {
							AtomicInteger count = new AtomicInteger(0);
							String message = note.getE().toLowerCase();
							terms.forEach(term -> {
								if(message.contains(term.getLabel().toLowerCase())) {
									count.incrementAndGet();
								}
							});
							return count.get();
						})
						.reduce(0, Integer::sum);
	}

}
