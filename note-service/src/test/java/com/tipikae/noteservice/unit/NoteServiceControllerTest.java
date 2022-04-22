package com.tipikae.noteservice.unit;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.tipikae.noteservice.controller.NoteServiceController;
import com.tipikae.noteservice.dto.NewNoteDTO;
import com.tipikae.noteservice.dto.NoteDTO;
import com.tipikae.noteservice.dto.UpdateNoteDTO;
import com.tipikae.noteservice.exception.NoteNotFoundException;
import com.tipikae.noteservice.service.INoteServiceService;

@WebMvcTest(controllers = NoteServiceController.class)
class NoteServiceControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private INoteServiceService noteService;
	
	private static final String ROOT = "/notes";
	
	private static String id;
	private static long patId;
	private static LocalDate date;
	private static String e;
	private static NoteDTO noteDTO;
	private static List<NoteDTO> noteDTOs;
	
	@BeforeAll
	private static void setUp() {
		id = "id";
		patId = 1;
		date = LocalDate.of(2022, 01, 01);
		e = "message";
		noteDTO = new NoteDTO(id, patId, date, e);
		noteDTOs = List.of(noteDTO);
	}


	@Test
	void addNewNoteReturns201AndNoteDTOWhenOk() throws Exception {
		when(noteService.addNote(any(NewNoteDTO.class))).thenReturn(noteDTO);
		mockMvc.perform(post(ROOT + "/")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"patId\": \"" + patId + "\","
						+ "\"date\": \"" + date + "\","
						+ "\"e\": \"" + e + "\"}"))
			.andExpect(status().is(201))
			.andExpect(jsonPath("$.patId", is(1)));
	}

	@Test
	void addNewNoteReturns400WhenZeroPatientId() throws Exception {
		mockMvc.perform(post(ROOT + "/0")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"patId\": \"0\","
						+ "\"date\": \"" + date + "\","
						+ "\"e\": \"" + e + "\"}"))
			.andExpect(status().is(400));
	}

	@Test
	void addNewNoteReturns400WhenFutureDate() throws Exception {
		LocalDate badDate = LocalDate.of(2030, 01, 01);
		mockMvc.perform(post(ROOT + "/")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"patId\": \"" + patId + "\","
						+ "\"date\": \"" + badDate + "\","
						+ "\"e\": \"" + e + "\"}"))
			.andExpect(status().is(400));
	}

	@Test
	void getNoteReturns200AndNoteDTOWhenOk() throws Exception {
		when(noteService.getNoteById(anyString())).thenReturn(noteDTO);
		mockMvc.perform(get(ROOT + "/id/" + id))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.e", is(e)));
	}

	@Test
	void getNoteReturns404WhenNotFound() throws Exception {
		doThrow(NoteNotFoundException.class).when(noteService).getNoteById(anyString());
		mockMvc.perform(get(ROOT + "/id/" + id))
			.andExpect(status().is(404));
	}

	@Test
	void getNoteReturns400WhenBlankId() throws Exception {
		mockMvc.perform(get(ROOT + "/id/"))
		.andExpect(status().is(400));
	}

	@Test
	void getPatientNotesReturns200AndNoteDTOsWhenOk() throws Exception {
		when(noteService.getPatientNotes(anyLong())).thenReturn(noteDTOs);
		mockMvc.perform(get(ROOT + "/search?patId=" + patId))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.[0].e", is(e)));
		
	}

	@Test
	void updateNoteReturns200WhenOk() throws Exception {
		doNothing().when(noteService).updateNote(anyString(), any(UpdateNoteDTO.class));
		mockMvc.perform(put(ROOT + "/" + id)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"e\": \"" + e + "\"}"))
			.andExpect(status().isOk());
	}

	@Test
	void updateNoteReturns404WhenNotFound() throws Exception {
		doThrow(NoteNotFoundException.class).when(noteService).updateNote(anyString(), any(UpdateNoteDTO.class));
		mockMvc.perform(put(ROOT + "/" + id)
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"e\": \"" + e + "\"}"))
			.andExpect(status().is(404));
	}

	@Test
	void updateNoteReturns400WhenBlankId() throws Exception {
		mockMvc.perform(put(ROOT + "/")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"e\": \"" + e + "\"}"))
			.andExpect(status().is(400));
	}

	@Test
	void deleteNoteReturns200WhenOk() throws Exception {
		doNothing().when(noteService).deleteNote(id);
		mockMvc.perform(delete(ROOT + "/" + id))
			.andExpect(status().isOk());
	}

	@Test
	void deleteNoteReturns404WhenNotFound() throws Exception {
		doThrow(NoteNotFoundException.class).when(noteService).deleteNote(id);
		mockMvc.perform(delete(ROOT + "/" + id))
			.andExpect(status().is(404));
	}

	@Test
	void deleteNoteReturns400WhenBlankId() throws Exception {
		mockMvc.perform(delete(ROOT + "/"))
			.andExpect(status().is(400));
	}

}
