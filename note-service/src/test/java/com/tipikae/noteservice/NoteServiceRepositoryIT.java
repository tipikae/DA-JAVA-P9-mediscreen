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
		long patId = 100;
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
		Note note2 = noteRepository.save(new Note(null, patId, LocalDate.now(), e));
		assertTrue(noteRepository.findByPatIdOrderByDateDesc(patId).size() > 0);
		assertEquals(note2.getId(), noteRepository.findByPatIdOrderByDateDesc(patId).get(0).getId());
		
		// update
		String updatedE = e + " updated";
		note.setE(updatedE);
		assertTrue(noteRepository.save(note).getE().equals(updatedE));
		
		// delete
		noteRepository.deleteById(id);
		noteRepository.deleteById(note2.getId());;
		assertTrue(noteRepository.findById(id).isEmpty());
	}

}
