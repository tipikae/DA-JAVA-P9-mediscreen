package com.tipikae.mediscreenUI.unit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.tipikae.mediscreenUI.client.INoteServiceClient;
import com.tipikae.mediscreenUI.client.IPatientServiceClient;
import com.tipikae.mediscreenUI.controller.NoteController;
import com.tipikae.mediscreenUI.dto.NewNoteDTO;
import com.tipikae.mediscreenUI.dto.UpdateNoteDTO;
import com.tipikae.mediscreenUI.exception.BadRequestException;
import com.tipikae.mediscreenUI.exception.NoteNotFoundException;
import com.tipikae.mediscreenUI.exception.PatientNotFoundException;
import com.tipikae.mediscreenUI.model.Note;

@WebMvcTest(controllers = NoteController.class)
class NoteControllerTest {
private static final String ROOT = "/note"; 
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IPatientServiceClient patientClient;
	
	@MockBean
	private INoteServiceClient noteClient;

//////////////////////////////////////////////////////////////////////////////////////////////////////////
// Get a note
//////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void getNoteReturns200AndNoteWhenOk() throws Exception {
		when(noteClient.getNote(anyString())).thenReturn(new Note());
		mockMvc.perform(get(ROOT + "/1/pouet"))
			.andExpect(status().isOk())
			.andExpect(view().name("note/get"));
	}
	
	@Test
	void getNoteReturns404WhenNNoteotFound() throws Exception {
		doThrow(NoteNotFoundException.class).when(noteClient.getNote(anyString()));
		mockMvc.perform(get(ROOT + "/1/pouet"))
			.andExpect(status().is(404))
			.andExpect(view().name("error/404"));
	}
	
	@Test
	void getNoteReturns404WhenPatientNotFound() throws Exception {
		doThrow(PatientNotFoundException.class).when(noteClient.getNote(anyString()));
		mockMvc.perform(get(ROOT + "/1/pouet"))
			.andExpect(status().is(404))
			.andExpect(view().name("error/404"));
	}
	
	@Test
	void getNoteReturns400WhenBlankId() throws Exception {
		mockMvc.perform(get(ROOT + "/1/"))
			.andExpect(status().is(400))
			.andExpect(view().name("error/400"));
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////
// Show add form
//////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void showAddFormNoteReturns200FormWhenOk() throws Exception {
		mockMvc.perform(get(ROOT + "/add"))
			.andExpect(status().isOk())
			.andExpect(view().name("note/add"));
		}

//////////////////////////////////////////////////////////////////////////////////////////////////////////
// Add a note
//////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void addNoteReturns3xxSuccessWhenOk() throws Exception {
		long patId = 1;
		NewNoteDTO newNoteDTO = new NewNoteDTO(patId, LocalDate.now(), "message");
		when(noteClient.addNote(any(NewNoteDTO.class))).thenReturn(new Note());
		mockMvc.perform(post(ROOT + "/add")
				.flashAttr("note", newNoteDTO))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/" + patId + "?success=New note added."));
	}
	
	@Test
	void addNoteReturns200WhenBadField() throws Exception {
		NewNoteDTO newNoteDTO = new NewNoteDTO(1, LocalDate.now(), "");
		when(noteClient.addNote(any(NewNoteDTO.class))).thenReturn(new Note());
		mockMvc.perform(post(ROOT + "/add")
				.flashAttr("note", newNoteDTO))
			.andExpect(status().isOk())
			.andExpect(view().name("note/add"));
	}
	
	@Test
	void addNoteReturns3xxErrorWhenBadRequest() throws Exception {
		long patId = 1;
		NewNoteDTO newNoteDTO = new NewNoteDTO(patId, LocalDate.now(), "message");
		doThrow(BadRequestException.class).when(noteClient).addNote(any(NewNoteDTO.class));
		mockMvc.perform(post(ROOT + "/add")
				.flashAttr("note", newNoteDTO))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/" + patId + "?error=Request error."));
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////
// Show update form
//////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void showUpdateFormNoteReturns200AndNoteWhenOk() throws Exception {
		when(noteClient.getNote(anyString())).thenReturn(new Note());
		mockMvc.perform(get(ROOT + "/update/pouet"))
			.andExpect(status().isOk())
			.andExpect(view().name("note/update"));
	}
	
	@Test
	void showUpdateFormNoteReturns404WhenNoteNotFound() throws Exception {
		doThrow(NoteNotFoundException.class).when(noteClient).getNote(anyString());
		mockMvc.perform(get(ROOT + "/update/pouet"))
			.andExpect(status().isOk())
			.andExpect(view().name("error/404"));
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////
// Update a note
//////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void updateNoteReturns3xxSuccessWhenOk() throws Exception {
		long patId = 1;
		UpdateNoteDTO updateNoteDTO = new UpdateNoteDTO("message");
		doNothing().when(noteClient).updateNote(anyString(), any(UpdateNoteDTO.class));
		mockMvc.perform(post(ROOT + "/update/pouet")
				.flashAttr("note", updateNoteDTO))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/" + patId + "?success=Note updated."));
	}
	
	@Test
	void updateNoteReturns3xxErrorWhenBadField() throws Exception {
		String id = "pouet";
		UpdateNoteDTO updateNoteDTO = new UpdateNoteDTO("");
		mockMvc.perform(post(ROOT + "/update/" + id)
				.flashAttr("note", updateNoteDTO))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/note/update/" + id + "?error=Note must not be empty. "));
	}
	
	@Test
	void updateNoteReturns3xxErrorWhenNoteNotFound() throws Exception {
		long patId = 1;
		UpdateNoteDTO updateNoteDTO = new UpdateNoteDTO("message");
		doThrow(NoteNotFoundException.class).when(noteClient).updateNote(anyString(), any(UpdateNoteDTO.class));
		mockMvc.perform(post(ROOT + "/update/pouet")
				.flashAttr("note", updateNoteDTO))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/" + patId + "?error=Note not found."));
	}
	
	@Test
	void updateNoteReturns3xxErrorWhenBadRequest() throws Exception {
		long patId = 1;
		UpdateNoteDTO updateNoteDTO = new UpdateNoteDTO("message");
		doThrow(BadRequestException.class).when(noteClient).updateNote(anyString(), any(UpdateNoteDTO.class));
		mockMvc.perform(post(ROOT + "/update/pouet")
				.flashAttr("note", updateNoteDTO))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/" + patId + "?error=Request error."));
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////
// Delete a note
//////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void deleteNoteReturns3xxSuccessWhenOk() throws Exception {
		long patId = 1;
		doNothing().when(noteClient).deleteNote(anyString());
		mockMvc.perform(delete(ROOT + "/pouet"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/" + patId + "?success=Note deleted."));
	}
	
	@Test
	void deleteNoteReturns3xxErrorWhenNoteNotFound() throws Exception {
		long patId = 1;
		doThrow(NoteNotFoundException.class).when(noteClient).deleteNote(anyString());
		mockMvc.perform(delete(ROOT + "/pouet"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/" + patId + "?error=Note not found."));
	}
	
	@Test
	void deleteNoteReturns3xxErrorWhenBadRequest() throws Exception {
		long patId = 1;
		doThrow(BadRequestException.class).when(noteClient).deleteNote(anyString());
		mockMvc.perform(delete(ROOT + "/pouet"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/" + patId + "?error=Request error."));
	}

}
