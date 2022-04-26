package com.tipikae.assessmentservice.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tipikae.assessmentservice.assessment.Gender;
import com.tipikae.assessmentservice.assessment.ITermCounter;
import com.tipikae.assessmentservice.assessment.ProcessDataImpl;
import com.tipikae.assessmentservice.assessment.Risk;

@ExtendWith(MockitoExtension.class)
class ProcessDataTest {

	@Mock
	private ITermCounter termCounter;
	
	@InjectMocks
	private ProcessDataImpl processData;
	
	private static Gender sexM;
	private static Gender sexF;
	private static int age29;
	private static int age30;
	
	
	@BeforeAll
	private static void setUp() {
		sexM = Gender.MALE;
		sexF = Gender.FEMALE;
		age29 = 29;
		age30 = 30;
	}

	@Test
	void getRiskReturnsNone() {
		String expected = Risk.NONE.getLabel();
		// no terms
		when(termCounter.countTerms(anyList())).thenReturn(0, 0, 0, 0);
		assertEquals(expected, processData.getRisk(age29, sexF, List.of()));
		assertEquals(expected, processData.getRisk(age29, sexM, List.of()));
		assertEquals(expected, processData.getRisk(age30, sexM, List.of()));
		assertEquals(expected, processData.getRisk(age30, sexM, List.of()));
		// 1 term
		when(termCounter.countTerms(anyList())).thenReturn(1, 1, 1, 1);
		assertEquals(expected, processData.getRisk(age29, sexF, List.of()));
		assertEquals(expected, processData.getRisk(age29, sexM, List.of()));
		assertEquals(expected, processData.getRisk(age30, sexM, List.of()));
		assertEquals(expected, processData.getRisk(age30, sexM, List.of()));
		// less 30 - 2 terms
		when(termCounter.countTerms(anyList())).thenReturn(2, 2);
		assertEquals(expected, processData.getRisk(age29, sexF, List.of()));
		assertEquals(expected, processData.getRisk(age29, sexM, List.of()));
		// less 30 - woman - 3 terms
		when(termCounter.countTerms(anyList())).thenReturn(3);
		assertEquals(expected, processData.getRisk(age29, sexF, List.of()));
	}

	@Test
	void getRiskReturnsBorderline() {
		String expected = Risk.BORDERLINE.getLabel();
		// more 30 - 2 terms
		when(termCounter.countTerms(anyList())).thenReturn(2, 2);
		assertEquals(expected, processData.getRisk(age30, sexF, List.of()));
		assertEquals(expected, processData.getRisk(age30, sexM, List.of()));
		// more 30 - 3 terms
		when(termCounter.countTerms(anyList())).thenReturn(3, 3);
		assertEquals(expected, processData.getRisk(age30, sexF, List.of()));
		assertEquals(expected, processData.getRisk(age30, sexM, List.of()));
		// more 30 - 4 terms
		when(termCounter.countTerms(anyList())).thenReturn(4, 4);
		assertEquals(expected, processData.getRisk(age30, sexF, List.of()));
		assertEquals(expected, processData.getRisk(age30, sexM, List.of()));
		// more 30 - 5 terms
		when(termCounter.countTerms(anyList())).thenReturn(5, 5);
		assertEquals(expected, processData.getRisk(age30, sexF, List.of()));
		assertEquals(expected, processData.getRisk(age30, sexM, List.of()));
	}

	@Test
	void getRiskReturnsInDanger() {
		String expected = Risk.INDANGER.getLabel();
		// less 30 - man - 3 terms
		when(termCounter.countTerms(anyList())).thenReturn(3);
		assertEquals(expected, processData.getRisk(age29, sexM, List.of()));
		// less 30 - man - 4 terms
		when(termCounter.countTerms(anyList())).thenReturn(4);
		assertEquals(expected, processData.getRisk(age29, sexM, List.of()));
		// less 30 - woman - 4 terms
		when(termCounter.countTerms(anyList())).thenReturn(4);
		assertEquals(expected, processData.getRisk(age29, sexF, List.of()));
		// less 30 - woman -  terms
		when(termCounter.countTerms(anyList())).thenReturn(5);
		assertEquals(expected, processData.getRisk(age29, sexF, List.of()));
		// less 30 - woman - 6 terms
		when(termCounter.countTerms(anyList())).thenReturn(6);
		assertEquals(expected, processData.getRisk(age29, sexF, List.of()));
		// more 30 - 6 terms
		when(termCounter.countTerms(anyList())).thenReturn(6, 6);
		assertEquals(expected, processData.getRisk(age30, sexM, List.of()));
		assertEquals(expected, processData.getRisk(age30, sexF, List.of()));
		// more 30 - 7 terms
		when(termCounter.countTerms(anyList())).thenReturn(7, 7);
		assertEquals(expected, processData.getRisk(age30, sexM, List.of()));
		assertEquals(expected, processData.getRisk(age30, sexF, List.of()));
	}

	@Test
	void getRiskReturnsEarlyOnset() {
		String expected = Risk.EARLYONSET.getLabel();
		// less 30 - man - 5 terms
		when(termCounter.countTerms(anyList())).thenReturn(5);
		assertEquals(expected, processData.getRisk(age29, sexM, List.of()));
		// less 30 - man - 6 terms
		when(termCounter.countTerms(anyList())).thenReturn(6);
		assertEquals(expected, processData.getRisk(age29, sexM, List.of()));
		// less 30 - man - 7 terms
		when(termCounter.countTerms(anyList())).thenReturn(7);
		assertEquals(expected, processData.getRisk(age29, sexM, List.of()));
		// less 30 - man - 8 terms
		when(termCounter.countTerms(anyList())).thenReturn(8);
		assertEquals(expected, processData.getRisk(age29, sexM, List.of()));
		// less 30 - woman - 7 terms
		when(termCounter.countTerms(anyList())).thenReturn(7);
		assertEquals(expected, processData.getRisk(age29, sexF, List.of()));
		// less 30 - woman - 8 terms
		when(termCounter.countTerms(anyList())).thenReturn(8);
		assertEquals(expected, processData.getRisk(age29, sexF, List.of()));
		// more 30 - 8 terms
		when(termCounter.countTerms(anyList())).thenReturn(8, 8);
		assertEquals(expected, processData.getRisk(age30, sexM, List.of()));
		assertEquals(expected, processData.getRisk(age30, sexF, List.of()));
		// more 30 - 9 terms
		when(termCounter.countTerms(anyList())).thenReturn(9, 9);
		assertEquals(expected, processData.getRisk(age30, sexM, List.of()));
		assertEquals(expected, processData.getRisk(age30, sexF, List.of()));
	}

}
