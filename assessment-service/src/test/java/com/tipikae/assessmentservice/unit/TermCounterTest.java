package com.tipikae.assessmentservice.unit;

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

import com.tipikae.assessmentservice.assessment.ITermReader;
import com.tipikae.assessmentservice.assessment.TermCounterImpl;
import com.tipikae.assessmentservice.model.Note;

@ExtendWith(MockitoExtension.class)
class TermCounterTest {
	
	@Mock
	private ITermReader termReader;
	
	@InjectMocks
	private TermCounterImpl termCounter;
	
	private static String id = "id";
	private static long patId = 1L;
	private static LocalDate date = LocalDate.of(2000, 01, 01);
	private static Note noteWithNoTerm;
	private static Note noteWith4Terms;
	private static Note noteWith1Term1;
	private static Note noteWith1Term2;
	private static Note noteWith1Term3;
	private static Note noteWith1Term4;
	private static List<String> terms;
	
	
	@BeforeAll
	private static void setUp() {
		noteWithNoTerm = new Note(id, patId, date, "test test");
		noteWith1Term1 = new Note(id, patId, date, "test Hemoglobin A1C");
		noteWith1Term2 = new Note(id, patId, date, "test Microalbumin");
		noteWith1Term3 = new Note(id, patId, date, "testWeight");
		noteWith1Term4 = new Note(id, patId, date, "testReaction");
		noteWith4Terms = new Note(id, patId, date, "test Abnormal test Antibodies testCholesteroltest  Smoker");
		terms = List.of("Hemoglobin A1C", "Microalbumin", "Height", "Weight", "Smoker", "Abnormal", 
				"Cholesterol", "Dizziness", "Relapse", "Reaction", "Antibodies");
	}

	@Test
	void countTermsReturns0When0Term() {
		when(termReader.read()).thenReturn(terms);
		assertEquals(0, termCounter.countTerms(List.of(noteWithNoTerm, noteWithNoTerm, noteWithNoTerm)));
	}

	@Test
	void countTermsReturns1When1() {
		when(termReader.read()).thenReturn(terms);
		assertEquals(1, termCounter.countTerms(List.of(noteWithNoTerm, noteWith1Term1, noteWithNoTerm)));
	}

	@Test
	void countTermsReturns4When1NoteWith4Terms() {
		when(termReader.read()).thenReturn(terms);
		assertEquals(4, termCounter.countTerms(List.of(noteWithNoTerm, noteWith4Terms, noteWithNoTerm)));
	}

	@Test
	void countTermsReturns4When4NotesWith1Term() {
		when(termReader.read()).thenReturn(terms);
		assertEquals(4, termCounter.countTerms(List.of(noteWithNoTerm, noteWith1Term1, noteWith1Term2, 
				noteWithNoTerm, noteWith1Term3, noteWithNoTerm, noteWith1Term4)));
	}

}
