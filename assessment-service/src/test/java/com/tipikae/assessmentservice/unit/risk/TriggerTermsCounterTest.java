package com.tipikae.assessmentservice.unit.risk;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tipikae.assessmentservice.model.Note;
import com.tipikae.assessmentservice.model.Trigger;
import com.tipikae.assessmentservice.repository.ITriggerRepository;
import com.tipikae.assessmentservice.service.TriggerTermsCounter;

@ExtendWith(MockitoExtension.class)
class TriggerTermsCounterTest {
	
	@Mock
	private ITriggerRepository triggerRepository;
	
	@InjectMocks
	private TriggerTermsCounter termsCounter;
	
	private static String id = "id";
	private static long patId = 1L;
	private static LocalDate date = LocalDate.of(2000, 01, 01);
	private static Note noteWithNoTerm;
	private static Note noteWith4Terms;
	private static Note noteWith1Term1;
	private static Note noteWith1Term2;
	private static Note noteWith1Term3;
	private static Note noteWith1Term4;
	private static List<Trigger> terms;
	
	
	@BeforeAll
	private static void setUp() {
		noteWithNoTerm = new Note(id, patId, date, "test test");
		noteWith1Term1 = new Note(id, patId, date, "test Hemoglobin A1C");
		noteWith1Term2 = new Note(id, patId, date, "test Microalbumin");
		noteWith1Term3 = new Note(id, patId, date, "testWeight");
		noteWith1Term4 = new Note(id, patId, date, "testReaction");
		noteWith4Terms = new Note(id, patId, date, "test Abnormal test Antibodies "
				+ "testCholesteroltest  Smoker");
		terms = List.of(
				new Trigger(0, "Hemoglobin A1C"), 
				new Trigger(0, "Microalbumin"), 
				new Trigger(0, "Height"), 
				new Trigger(0, "Weight"), 
				new Trigger(0, "Smoker"), 
				new Trigger(0, "Abnormal"), 
				new Trigger(0, "Cholesterol"), 
				new Trigger(0, "Dizziness"),
				new Trigger(0, "Relapse"),
				new Trigger(0, "Reaction"),
				new Trigger(0, "Antibodies"));
	}

	@Test
	void countTermsReturns0When0Term() throws Exception {
		when(triggerRepository.findAll()).thenReturn(terms);
		assertEquals(0, termsCounter.countTerms(List.of(noteWithNoTerm, noteWithNoTerm, noteWithNoTerm)));
	}

	@Test
	void countTermsReturns1When1() throws Exception {
		when(triggerRepository.findAll()).thenReturn(terms);
		assertEquals(1, termsCounter.countTerms(List.of(noteWithNoTerm, noteWith1Term1, noteWithNoTerm)));
	}

	@Test
	void countTermsReturns4When1NoteWith4Terms() throws Exception {
		when(triggerRepository.findAll()).thenReturn(terms);
		assertEquals(4, termsCounter.countTerms(List.of(noteWithNoTerm, noteWith4Terms, noteWithNoTerm)));
	}

	@Test
	void countTermsReturns4When4NotesWith1Term() throws Exception {
		when(triggerRepository.findAll()).thenReturn(terms);
		assertEquals(4, termsCounter.countTerms(List.of(noteWithNoTerm, noteWith1Term1, noteWith1Term2, 
				noteWithNoTerm, noteWith1Term3, noteWithNoTerm, noteWith1Term4)));
	}

}
