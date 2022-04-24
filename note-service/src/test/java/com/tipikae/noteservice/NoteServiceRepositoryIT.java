package com.tipikae.noteservice;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.noteservice.model.Note;
import com.tipikae.noteservice.repository.INoteServiceRepository;

@SpringBootTest
class NoteServiceRepositoryIT {
	
	@Autowired
	private INoteServiceRepository noteRepository;

	@Test
	void test() {
		String id = null;
		long patId = 1;
		LocalDate date = LocalDate.of(2022, 01, 01);
		String e = "prout";
		
		// save
		Note note = new Note(id, patId, date, e);
		Note saved = noteRepository.save(note);
		assertEquals(patId, saved.getPatId());
		
		// findById
		id = saved.getId();
		assertEquals(e, noteRepository.findById(id).get().getE());
		
		// findAll
		assertTrue(noteRepository.findByPatId(patId).size() > 0);
		
		// update
		String updatedE = e + " updated";
		note.setE(updatedE);
		assertTrue(noteRepository.save(note).getE().equals(updatedE));
		
		// delete
		noteRepository.deleteById(id);
		assertTrue(noteRepository.findById(id).isEmpty());
	}

}
