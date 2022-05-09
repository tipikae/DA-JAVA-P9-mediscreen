/**
 * 
 */
package com.tipikae.assessmentservice.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tipikae.assessmentservice.model.Note;
import com.tipikae.assessmentservice.repository.ITriggerRepository;

/**
 * Terms reader.
 * @author tipikae
 * @version 1.0
 *
 */
@Service
public class TriggerTermsCounter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TriggerTermsCounter.class);
	
	@Autowired
	private ITriggerRepository triggerRepository;
	
	/**
	 * Count terms in notes list.
	 * @param notes List
	 * @return int
	 */
	public int countTerms(List<Note> notes) {
		List<String> terms = triggerRepository.findAll().stream()
				.map(trigger -> trigger.getTerm())
				.collect(Collectors.toList());
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
