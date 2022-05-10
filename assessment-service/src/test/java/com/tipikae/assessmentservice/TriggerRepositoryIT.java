package com.tipikae.assessmentservice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.assessmentservice.model.Trigger;
import com.tipikae.assessmentservice.repository.ITriggerRepository;

@SpringBootTest
class TriggerRepositoryIT {
	
	@Autowired
	private ITriggerRepository triggerRepository;
	
	@Test
	void test() {
		long id;
		String term = "term";
		
		// save
		Trigger trigger = triggerRepository.save(new Trigger(0L, term));
		id = trigger.getId();
		
		// find by id
		assertEquals(term, triggerRepository.findById(id).get().getTerm());
		
		// find all
		assertTrue(triggerRepository.findAll().size() > 0);
		
		// update
		String updatedTrigger = "updatedTrigger";
		trigger.setTerm(updatedTrigger);
		triggerRepository.save(trigger);
		assertEquals(updatedTrigger, triggerRepository.findById(id).get().getTerm());
		
		// delete
		triggerRepository.delete(trigger);
		assertTrue(triggerRepository.findById(id).isEmpty());
	}

}
