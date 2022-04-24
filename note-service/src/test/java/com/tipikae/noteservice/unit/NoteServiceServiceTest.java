package com.tipikae.noteservice.unit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.tipikae.noteservice.converter.IConverterNoteDTO;
import com.tipikae.noteservice.dto.NewNoteDTO;
import com.tipikae.noteservice.dto.NoteDTO;
import com.tipikae.noteservice.dto.UpdateNoteDTO;
import com.tipikae.noteservice.exception.NoteNotFoundException;
import com.tipikae.noteservice.model.Note;
import com.tipikae.noteservice.repository.INoteServiceRepository;
import com.tipikae.noteservice.service.NoteServiceServiceImpl;

@ExtendWith(MockitoExtension.class)
class NoteServiceServiceTest {
	
	@Mock
	private IConverterNoteDTO noteConverter;
	
	@Mock
	private INoteServiceRepository noteRepository;
	
	@InjectMocks
	private NoteServiceServiceImpl noteService;
	
	private static String id;
	private static long patId;
	private static LocalDate date;
	private static String e;
	private static String updatedE;
	private static Note note;
	private static NoteDTO noteDTO;
	private static NewNoteDTO newNoteDTO;
	private static UpdateNoteDTO updateNoteDTO;
	private static List<Note> notes;
	private static List<NoteDTO> noteDTOs;
	
	@BeforeAll
	private static void setUp() {
		id = "id";
		patId = 1;
		e = "message";
		date = LocalDate.now();
		note = new Note(id, patId, date, e);
		noteDTO = new NoteDTO(id, patId, date, e);
		newNoteDTO = new NewNoteDTO(patId, e);
		updatedE = e + " updated";
		updateNoteDTO = new UpdateNoteDTO(updatedE);
		notes = List.of(note);
		noteDTOs = List.of(noteDTO);
	}

	@Test
	void addNoteReturnsNoteDTOWhenOk() {
		when(noteConverter.convertNewNoteDTOToNote(any(NewNoteDTO.class))).thenReturn(note);
		when(noteRepository.save(any(Note.class))).thenReturn(note);
		when(noteConverter.convertNoteToNoteDTO(any(Note.class))).thenReturn(noteDTO);
		assertEquals(e, noteService.addNote(newNoteDTO).getE());
	}

	@Test
	void getNoteByIdReturnsNoteDTOWhenOk() throws NoteNotFoundException {
		when(noteRepository.findById(anyString())).thenReturn(Optional.of(note));
		when(noteConverter.convertNoteToNoteDTO(any(Note.class))).thenReturn(noteDTO);
		assertEquals(date, noteService.getNoteById(id).getDate());
	}

	@Test
	void getNoteByIdThrowsExceptionWhenNotFound() {
		when(noteRepository.findById(anyString())).thenReturn(Optional.empty());
		assertThrows(NoteNotFoundException.class, () -> noteService.getNoteById(id));
	}

	@Test
	void getPatientNotesReturnsNoteDTOsWhenOk() {
		when(noteRepository.findByPatId(anyLong())).thenReturn(notes);
		when(noteConverter.convertNotesToNoteDTOs(anyList())).thenReturn(noteDTOs);
		assertEquals(noteDTOs.size(), noteService.getPatientNotes(patId).size());
	}

	@Test
	void updateNoteCallsSaveWhenOk() throws NoteNotFoundException {
		when(noteRepository.findById(anyString())).thenReturn(Optional.of(note));
		note.setE(updatedE);
		when(noteConverter.convertUpdateNoteDTOToNote(any(UpdateNoteDTO.class), any(Note.class))).thenReturn(note);
		noteService.updateNote(id, updateNoteDTO);
		verify(noteRepository).save(any(Note.class));
	}

	@Test
	void updateNoteThrowsExceptionWhenNotFound() {
		when(noteRepository.findById(anyString())).thenReturn(Optional.empty());
		assertThrows(NoteNotFoundException.class, () -> noteService.updateNote(id, updateNoteDTO));
	}

	@Test
	void deleteNoteCallsDeleteWhenOk() throws NoteNotFoundException {
		when(noteRepository.findById(anyString())).thenReturn(Optional.of(note));
		noteService.deleteNote(id);
		verify(noteRepository).deleteById(anyString());
	}

	@Test
	void deleteNoteThrowsExceptionWhenNotFound() {
		when(noteRepository.findById(anyString())).thenReturn(Optional.empty());
		assertThrows(NoteNotFoundException.class, () -> noteService.deleteNote(id));
	}

}
