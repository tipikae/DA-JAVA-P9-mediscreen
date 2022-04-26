package com.tipikae.assessmentservice.unit;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.tipikae.assessmentservice.assessment.ITermCounter;
import com.tipikae.assessmentservice.assessment.Term;
import com.tipikae.assessmentservice.assessment.TermCounterImpl;
import com.tipikae.assessmentservice.model.Note;

class TermCounterTest {
	
	private ITermCounter termCounter = new TermCounterImpl();
	
	private static String id = "id";
	private static long patId = 1L;
	private static LocalDate date = LocalDate.of(2000, 01, 01);
	private static Note noteWithNoTerm;
	private static Note noteWith4Terms;
	private static Note noteWith1Term1;
	private static Note noteWith1Term2;
	private static Note noteWith1Term3;
	private static Note noteWith1Term4;
	
	
	@BeforeAll
	private static void setUp() {
		noteWithNoTerm = new Note(id, patId, date, "test test");
		noteWith1Term1 = new Note(id, patId, date, "test " + Term.HEMOGLOBINEA1C.getLabel());
		noteWith1Term2 = new Note(id, patId, date, "test " + Term.MICROALBUMINE.getLabel());
		noteWith1Term3 = new Note(id, patId, date, "test" + Term.POIDS.getLabel());
		noteWith1Term4 = new Note(id, patId, date, "test" + Term.REACTION.getLabel());
		noteWith4Terms = new Note(id, patId, date, "test " + Term.ANORMAL.getLabel() + " test " 
				+ Term.ANTICORPS.getLabel() + "test" + Term.CHOLESTEROL.getLabel() + "test  " 
				+ Term.FUMEUR.getLabel());
	}

	@Test
	void countTermsReturns0When0Term() {
		assertEquals(0, termCounter.countTerms(List.of(noteWithNoTerm, noteWithNoTerm, noteWithNoTerm)));
	}

	@Test
	void countTermsReturns1When1() {
		assertEquals(1, termCounter.countTerms(List.of(noteWithNoTerm, noteWith1Term1, noteWithNoTerm)));
	}

	@Test
	void countTermsReturns4When1NoteWith4Terms() {
		assertEquals(4, termCounter.countTerms(List.of(noteWithNoTerm, noteWith4Terms, noteWithNoTerm)));
	}

	@Test
	void countTermsReturns4When4NotesWith1Term() {
		assertEquals(4, termCounter.countTerms(List.of(noteWithNoTerm, noteWith1Term1, noteWith1Term2, 
				noteWithNoTerm, noteWith1Term3, noteWithNoTerm, noteWith1Term4)));
	}

}
