package com.tipikae.noteservice;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.noteservice.dto.NewNoteDTO;
import com.tipikae.noteservice.dto.NoteDTO;
import com.tipikae.noteservice.dto.UpdateNoteDTO;
import com.tipikae.noteservice.exception.NoteNotFoundException;
import com.tipikae.noteservice.service.INoteServiceService;

@SpringBootTest
class NoteServiceIT {
	
	@Autowired
	private INoteServiceService noteService;

	@Test
	void test() throws NoteNotFoundException {
		String id;
		long patId = 1L;
		String e = "message";
		
		// save
		NewNoteDTO newNoteDTO = new NewNoteDTO(patId, e);
		NoteDTO noteDTO = noteService.addNote(newNoteDTO);
		id = noteDTO.getId();
		
		// get note by id
		assertEquals(e, noteService.getNoteById(id).getE());
		assertThrows(NoteNotFoundException.class, () -> noteService.getNoteById("pouet"));
		
		// get notes by patientId
		assertTrue(noteService.getPatientNotes(patId).size() > 0);
		
		// update a note
		String updatedE = "updatedE";
		UpdateNoteDTO updateNoteDTO = new UpdateNoteDTO(updatedE);
		noteService.updateNote(id, updateNoteDTO);
		assertEquals(updatedE, noteService.getNoteById(id).getE());
		
		// delete a note
		noteService.deleteNote(id);
		assertThrows(NoteNotFoundException.class, () -> noteService.getNoteById(id));
	}

}
