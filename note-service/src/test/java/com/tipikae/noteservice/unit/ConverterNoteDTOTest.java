package com.tipikae.noteservice.unit;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.tipikae.noteservice.converter.ConverterNoteDTOImpl;
import com.tipikae.noteservice.converter.IConverterNoteDTO;
import com.tipikae.noteservice.dto.NewNoteDTO;
import com.tipikae.noteservice.dto.UpdateNoteDTO;
import com.tipikae.noteservice.model.Note;

class ConverterNoteDTOTest {
	
	private IConverterNoteDTO noteConverter = new ConverterNoteDTOImpl();
	
	private String id = "id";
	private long patId = 1;
	private LocalDate date = LocalDate.of(2022, 01, 01);
	private String e = "message";

	@Test
	void convertNewNoteDTOToNote() {
		NewNoteDTO newNoteDTO = new NewNoteDTO(patId, date, e);
		assertEquals(patId, noteConverter.convertNewNoteDTOToNote(newNoteDTO).getPatId());
	}

	@Test
	void convertUpdateNoteDTOToNote() {
		Note note = new Note(id, patId, date, e);
		String updatedMessage = e + "2";
		UpdateNoteDTO updateNoteDTO = new UpdateNoteDTO(updatedMessage);
		assertEquals(updatedMessage, noteConverter.convertUpdateNoteDTOToNote(updateNoteDTO, note).getE());
	}

	@Test
	void convertNoteToNoteDTO() {
		Note note = new Note(id, patId, date, e);
		assertEquals(patId, noteConverter.convertNoteToNoteDTO(note).getPatId());
	}

	@Test
	void convertNotesToDTOs() {
		List<Note> notes = List.of(new Note(id, patId, date, e));
		assertTrue(noteConverter.convertNotesToNoteDTOs(notes).size() > 0);
		assertEquals(id, noteConverter.convertNotesToNoteDTOs(notes).get(0).getId());
	}

}
