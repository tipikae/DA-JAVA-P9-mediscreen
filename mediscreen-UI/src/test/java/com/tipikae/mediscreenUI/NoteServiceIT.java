package com.tipikae.mediscreenUI;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.mediscreenUI.dto.NewNoteDTO;
import com.tipikae.mediscreenUI.dto.UpdateNoteDTO;
import com.tipikae.mediscreenUI.exception.BadRequestException;
import com.tipikae.mediscreenUI.exception.HttpClientException;
import com.tipikae.mediscreenUI.exception.NotFoundException;
import com.tipikae.mediscreenUI.model.Note;
import com.tipikae.mediscreenUI.service.INoteService;

@SpringBootTest
class NoteServiceIT {
	
	@Autowired
	private INoteService noteService;

	@Test
	void test() throws BadRequestException, HttpClientException, NotFoundException {
		String id = null;
		long patId = 10;
		String e = "message";
		
		// save
		NewNoteDTO newNoteDTO = new NewNoteDTO(patId, e);
		Note note = noteService.addNote(newNoteDTO);
		assertEquals(e, note.getE());
		assertThrows(BadRequestException.class, () -> noteService.addNote(new NewNoteDTO(patId, "")));
		
		// get
		id = note.getId();
		assertEquals(LocalDate.now(), noteService.getNote(id).getDate());
		assertThrows(NotFoundException.class, () -> noteService.getNote("pouet"));
		assertThrows(BadRequestException.class, () -> noteService.getNote(""));
		
		// get all patient's notes
		assertTrue(noteService.getPatientNotes(patId).size() > 0);
		assertThrows(BadRequestException.class, () -> noteService.getPatientNotes(0));
		
		// update
		String updatedE = e + " updated";
		UpdateNoteDTO updateNoteDTO = new UpdateNoteDTO(updatedE);
		noteService.updateNote(id, updateNoteDTO);
		assertEquals(updatedE, noteService.getNote(id).getE());
		assertThrows(NotFoundException.class, () -> noteService.updateNote("pouet", updateNoteDTO));
		assertThrows(BadRequestException.class, () -> noteService.updateNote("", updateNoteDTO));
		assertThrows(BadRequestException.class, () -> noteService.updateNote("pouet", new UpdateNoteDTO("")));
		
		// delete
		noteService.deleteNote(id);
		final String finalId = id;
		assertThrows(NotFoundException.class, () -> noteService.getNote(finalId));
		assertThrows(BadRequestException.class, () -> noteService.getNote(""));
	}

}
