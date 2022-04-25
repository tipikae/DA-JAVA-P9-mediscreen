package com.tipikae.assessmentservice.unit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.assessmentservice.assessment.IAssessment;
import com.tipikae.assessmentservice.assessment.ProcessDataImpl;
import com.tipikae.assessmentservice.util.IUtil;

@SpringBootTest
class ProcessDataTest {
	
	@Mock
	private IAssessment assessment;
	
	@Mock
	private IUtil util;
	
	@InjectMocks
	private ProcessDataImpl processData;

	@Test
	void calculate() {
		
	}

}
