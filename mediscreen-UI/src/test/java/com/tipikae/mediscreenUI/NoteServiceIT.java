package com.tipikae.mediscreenUI;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.tipikae.mediscreenUI.client.INoteServiceClient;
import com.tipikae.mediscreenUI.dto.NewNoteDTO;
import com.tipikae.mediscreenUI.dto.UpdateNoteDTO;
import com.tipikae.mediscreenUI.exception.BadRequestException;
import com.tipikae.mediscreenUI.exception.HttpClientException;
import com.tipikae.mediscreenUI.exception.NoteNotFoundException;
import com.tipikae.mediscreenUI.model.Note;

@SpringBootTest
class NoteServiceIT {
	
	@Autowired
	private INoteServiceClient noteClient;

	@Test
	void test() throws BadRequestException, HttpClientException, NoteNotFoundException {
		String id = null;
		long patId = 10;
		String e = "message";
		
		// save
		NewNoteDTO newNoteDTO = new NewNoteDTO(patId, e);
		Note note = noteClient.addNote(newNoteDTO);
		assertEquals(e, note.getE());
		assertThrows(BadRequestException.class, () -> noteClient.addNote(new NewNoteDTO(patId, "")));
		
		// get
		id = note.getId();
		assertEquals(LocalDate.now(), noteClient.getNote(id).getDate());
		assertThrows(NoteNotFoundException.class, () -> noteClient.getNote("pouet"));
		assertThrows(BadRequestException.class, () -> noteClient.getNote(""));
		
		// get all patient's notes
		assertTrue(noteClient.getPatientNotes(patId).size() == 1);
		assertThrows(BadRequestException.class, () -> noteClient.getPatientNotes(0));
		
		// update
		String updatedE = e + " updated";
		UpdateNoteDTO updateNoteDTO = new UpdateNoteDTO(updatedE);
		noteClient.updateNote(id, updateNoteDTO);
		assertEquals(updatedE, noteClient.getNote(id).getE());
		assertThrows(NoteNotFoundException.class, () -> noteClient.updateNote("pouet", updateNoteDTO));
		assertThrows(BadRequestException.class, () -> noteClient.updateNote("", updateNoteDTO));
		assertThrows(BadRequestException.class, () -> noteClient.updateNote("pouet", new UpdateNoteDTO("")));
		
		// delete
		noteClient.deleteNote(id);
		final String finalId = id;
		assertThrows(NoteNotFoundException.class, () -> noteClient.getNote(finalId));
		assertThrows(BadRequestException.class, () -> noteClient.getNote(""));
	}

}
