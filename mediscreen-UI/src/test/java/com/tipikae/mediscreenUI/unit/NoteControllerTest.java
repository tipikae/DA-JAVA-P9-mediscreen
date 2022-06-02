package com.tipikae.mediscreenUI.unit;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.tipikae.mediscreenUI.controller.NoteController;
import com.tipikae.mediscreenUI.dto.NewNoteDTO;
import com.tipikae.mediscreenUI.dto.UpdateNoteDTO;
import com.tipikae.mediscreenUI.exception.BadRequestException;
import com.tipikae.mediscreenUI.exception.NotFoundException;
import com.tipikae.mediscreenUI.model.Note;
import com.tipikae.mediscreenUI.model.Patient;
import com.tipikae.mediscreenUI.service.INoteService;
import com.tipikae.mediscreenUI.service.IPatientService;

@WebMvcTest(controllers = NoteController.class)
class NoteControllerTest {
private static final String ROOT = "/note"; 
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private IPatientService patientService;
	
	@MockBean
	private INoteService noteService;

//////////////////////////////////////////////////////////////////////////////////////////////////////////
// Get a note
//////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void getNoteReturns200AndNoteWhenOk() throws Exception {
		when(noteService.getNote(anyString())).thenReturn(new Note());
		when(patientService.getPatient(anyLong())).thenReturn(new Patient());
		mockMvc.perform(get(ROOT + "/1/pouet"))
			.andExpect(status().isOk())
			.andExpect(view().name("note/get"));
	}
	
	@Test
	void getNoteReturns404WhenNoteNotFound() throws Exception {
		when(patientService.getPatient(anyLong())).thenReturn(new Patient());
		doThrow(NotFoundException.class).when(noteService).getNote(anyString());
		mockMvc.perform(get(ROOT + "/1/pouet"))
			.andExpect(status().is(200))
			.andExpect(view().name("error/404"));
	}
	
	@Test
	void getNoteReturns404WhenPatientNotFound() throws Exception {
		doThrow(NotFoundException.class).when(patientService).getPatient(anyLong());
		mockMvc.perform(get(ROOT + "/1/pouet"))
			.andExpect(status().is(200))
			.andExpect(view().name("error/404"));
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////
// Show add form
//////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void showAddFormNoteReturns200FormWhenOk() throws Exception {
		when(patientService.getPatient(anyLong())).thenReturn(new Patient());
		mockMvc.perform(get(ROOT + "/add/1"))
			.andExpect(status().isOk())
			.andExpect(view().name("note/add"));
		}

//////////////////////////////////////////////////////////////////////////////////////////////////////////
// Add a note
//////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void addNoteReturns3xxSuccessWhenOk() throws Exception {
		long patId = 1;
		NewNoteDTO newNoteDTO = new NewNoteDTO(patId, "message");
		when(noteService.addNote(any(NewNoteDTO.class))).thenReturn(new Note());
		mockMvc.perform(post(ROOT + "/add/" + patId)
				.flashAttr("note", newNoteDTO))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/" + patId + "?success=New note added."));
	}
	
	@Test
	void addNoteReturns3xxErrorWhenBadRequest() throws Exception {
		long patId = 1;
		NewNoteDTO newNoteDTO = new NewNoteDTO(patId, "message");
		doThrow(BadRequestException.class).when(noteService).addNote(any(NewNoteDTO.class));
		mockMvc.perform(post(ROOT + "/add/" + patId)
				.flashAttr("note", newNoteDTO))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/" + patId + "?error=Request error."));
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////
// Show update form
//////////////////////////////////////////////////////////////////////////////////////////////////////////
	@Test
	void showUpdateFormNoteReturns200AndNoteWhenOk() throws Exception {
		when(noteService.getNote(anyString())).thenReturn(new Note());
		when(patientService.getPatient(anyLong())).thenReturn(new Patient());
		mockMvc.perform(get(ROOT + "/update/1/pouet"))
			.andExpect(status().isOk())
			.andExpect(view().name("note/update"));
	}
	
	@Test
	void showUpdateFormNoteReturns404WhenNoteNotFound() throws Exception {
		when(patientService.getPatient(anyLong())).thenReturn(new Patient());
		doThrow(NotFoundException.class).when(noteService).getNote(anyString());
		mockMvc.perform(get(ROOT + "/update/1/pouet"))
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
		doNothing().when(noteService).updateNote(anyString(), any(UpdateNoteDTO.class));
		mockMvc.perform(post(ROOT + "/update/" + patId + "/pouet")
				.flashAttr("note", updateNoteDTO))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/" + patId + "?success=Note updated."));
	}
	
	@Test
	void updateNoteReturns3xxErrorWhenNoteNotFound() throws Exception {
		long patId = 1;
		UpdateNoteDTO updateNoteDTO = new UpdateNoteDTO("message");
		doThrow(NotFoundException.class).when(noteService).updateNote(anyString(), any(UpdateNoteDTO.class));
		mockMvc.perform(post(ROOT + "/update/" + patId + "/pouet")
				.flashAttr("note", updateNoteDTO))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/" + patId + "?error=Note not found."));
	}
	
	@Test
	void updateNoteReturns3xxErrorWhenBadRequest() throws Exception {
		long patId = 1;
		UpdateNoteDTO updateNoteDTO = new UpdateNoteDTO("message");
		doThrow(BadRequestException.class).when(noteService).updateNote(anyString(), any(UpdateNoteDTO.class));
		mockMvc.perform(post(ROOT + "/update/" + patId + "/pouet")
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
		doNothing().when(noteService).deleteNote(anyString());
		mockMvc.perform(get(ROOT + "/delete/" + patId + "/pouet"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/" + patId + "?success=Note deleted."));
	}
	
	@Test
	void deleteNoteReturns3xxErrorWhenNoteNotFound() throws Exception {
		long patId = 1;
		doThrow(NotFoundException.class).when(noteService).deleteNote(anyString());
		mockMvc.perform(get(ROOT + "/delete/" + patId + "/pouet"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/" + patId + "?error=Note not found."));
	}
	
	@Test
	void deleteNoteReturns3xxErrorWhenBadRequest() throws Exception {
		long patId = 1;
		doThrow(BadRequestException.class).when(noteService).deleteNote(anyString());
		mockMvc.perform(get(ROOT + "/delete/" + patId + "/pouet"))
			.andExpect(status().is3xxRedirection())
			.andExpect(view().name("redirect:/patient/" + patId + "?error=Request error."));
	}

}
