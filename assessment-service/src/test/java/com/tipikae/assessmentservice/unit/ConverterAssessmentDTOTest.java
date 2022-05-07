package com.tipikae.assessmentservice.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.tipikae.assessmentservice.dto.ConverterAssessmentDTOImpl;
import com.tipikae.assessmentservice.dto.IConverterAssessmentDTO;
import com.tipikae.assessmentservice.model.Assessment;

class ConverterAssessmentDTOTest {
	
	private IConverterAssessmentDTO assessmentConverter = new ConverterAssessmentDTOImpl();

	@Test
	void convertModelToDTOReturnsDTOWhenOk() {
		String message = "message";
		Assessment assessment = new Assessment(message);
		assertEquals(message, assessmentConverter.convertModelToDTO(assessment).getMessage());
	}

}
