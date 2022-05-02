package com.tipikae.assessmentservice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.assessmentservice.model.Formula;
import com.tipikae.assessmentservice.repository.IFormulaRepository;

@SpringBootTest
class FormulaRepositoryIT {
	
	@Autowired
	private IFormulaRepository formulaRepository;

	@Test
	void test() {
		long id;
		String formula = "formula";
		
		// save
		Formula formula1 = formulaRepository.save(new Formula(0L, 1L, formula));
		id = formula1.getId();
		
		// find by id
		assertEquals(formula, formulaRepository.findById(id).get().getForm());
		
		// find all
		assertTrue(formulaRepository.findAll().size() > 0);
		
		// update
		String updatedFormula = "updatedFormula";
		formula1.setForm(updatedFormula);
		formulaRepository.save(formula1);
		assertEquals(updatedFormula, formulaRepository.findById(id).get().getForm());
		
		// delete
		formulaRepository.delete(formula1);
		assertTrue(formulaRepository.findById(id).isEmpty());
	}

}
