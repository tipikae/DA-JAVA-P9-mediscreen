package com.tipikae.assessmentservice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.assessmentservice.model.Risk;
import com.tipikae.assessmentservice.repository.IRiskRepository;

@SpringBootTest
class RiskRepositoryIT {
	
	@Autowired
	private IRiskRepository riskRepository;

	@Test
	void test() {
		long id;
		String label = "label";
		
		// save
		Risk risk = riskRepository.save(new Risk(0L, label));
		id = risk.getId();
		
		// find by id
		assertEquals(label, riskRepository.findById(id).get().getLabel());
		
		// find all
		assertTrue(riskRepository.findAll().size() > 0);
		
		// update
		String updatedRisk = "updatedTrigger";
		risk.setLabel(updatedRisk);
		riskRepository.save(risk);
		assertEquals(updatedRisk, riskRepository.findById(id).get().getLabel());
		
		// delete
		riskRepository.delete(risk);
		assertTrue(riskRepository.findById(id).isEmpty());
	}

}
