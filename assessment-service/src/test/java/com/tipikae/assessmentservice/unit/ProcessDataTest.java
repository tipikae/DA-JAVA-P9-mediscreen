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

import com.tipikae.assessmentservice.assessment.ITermCounter;
import com.tipikae.assessmentservice.assessment.ProcessDataImpl;
import com.tipikae.assessmentservice.assessment.Risk;

@ExtendWith(MockitoExtension.class)
class ProcessDataTest {

	@Mock
	private ITermCounter termCounter;
	
	@InjectMocks
	private ProcessDataImpl processData;
	
	private static char sexM;
	private static char sexF;
	private static int age29;
	private static int age30;
	
	
	@BeforeAll
	private static void setUp() {
		sexM = 'M';
		sexF = 'F';
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
	}

	@Test
	void getRiskReturnsBorderline() {
		String none = Risk.NONE.getLabel();
		String expected = Risk.BORDERLINE.getLabel();
		// less 30 - 2 terms
		when(termCounter.countTerms(anyList())).thenReturn(2, 2);
		assertEquals(none, processData.getRisk(age29, sexF, List.of()).equals(expected));
		assertEquals(none, processData.getRisk(age29, sexM, List.of()).equals(expected));
		// more 30 - 2 terms
		when(termCounter.countTerms(anyList())).thenReturn(2, 2);
		assertEquals(expected, processData.getRisk(age30, sexF, List.of()));
		assertEquals(expected, processData.getRisk(age30, sexM, List.of()));
	}

	@Test
	void getRiskReturnsInDanger() {
		String expected = Risk.INDANGER.getLabel();
		// man - less 30 - 3 terms
		when(termCounter.countTerms(anyList())).thenReturn(3);
		assertEquals(expected, processData.getRisk(age29, sexM, List.of()));
		// woman - less 30 - 4 terms
		when(termCounter.countTerms(anyList())).thenReturn(4);
		assertEquals(expected, processData.getRisk(age29, sexF, List.of()));
		// more 30 - 6 terms
		when(termCounter.countTerms(anyList())).thenReturn(6);
		assertEquals(expected, processData.getRisk(age30, sexM, List.of()));
		assertEquals(expected, processData.getRisk(age30, sexF, List.of()));
	}

	@Test
	void getRiskReturnsEarlyOnset() {
		String expected = Risk.EARLYONSET.getLabel();
		// man - less 30 - 5 terms
		when(termCounter.countTerms(anyList())).thenReturn(5);
		assertEquals(expected, processData.getRisk(age29, sexM, List.of()));
		// woman - less 30 - 7 terms
		when(termCounter.countTerms(anyList())).thenReturn(7);
		assertEquals(expected, processData.getRisk(age29, sexF, List.of()));
		// more 30 - 8 terms
		when(termCounter.countTerms(anyList())).thenReturn(8);
		assertEquals(expected, processData.getRisk(age30, sexM, List.of()));
		assertEquals(expected, processData.getRisk(age30, sexF, List.of()));
	}

}
