/**
 * 
 */
package com.tipikae.noteservice.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tipikae.noteservice.dto.NewNoteDTO;
import com.tipikae.noteservice.dto.NoteDTO;
import com.tipikae.noteservice.dto.UpdateNoteDTO;
import com.tipikae.noteservice.exception.NoteNotFoundException;
import com.tipikae.noteservice.service.INoteServiceService;

/**
 * Note controller.
 * @author tipikae
 * @version 1.0
 *
 */
@RestController
@Validated
@RequestMapping("/notes")
public class NoteServiceController {

	private static final Logger LOGGER = LoggerFactory.getLogger(NoteServiceController.class);
	
	@Autowired
	private INoteServiceService noteService;
	
	@PostMapping(value = "/", consumes = {"application/json"})
	public ResponseEntity<NoteDTO> addNewNote(@RequestBody @Valid NewNoteDTO newNoteDTO) {
		LOGGER.info("Adding a new note");
		NoteDTO noteDTO = noteService.addNote(newNoteDTO);
		
		return new ResponseEntity<NoteDTO>(noteDTO, HttpStatus.CREATED);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<NoteDTO> getNote(@PathVariable("id") @NotBlank String id) 
			throws NoteNotFoundException {
		LOGGER.info("Getting a note id = " + id);
		NoteDTO noteDTO = noteService.getNoteById(id);
		
		return new ResponseEntity<NoteDTO>(noteDTO, HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<NoteDTO>> getPatientNotes(
			@RequestParam(name = "patId", required = true) @NotNull @Positive long patId) {
		LOGGER.info("Getting notes for patient with id=" + patId);
		List<NoteDTO> noteDTOs = noteService.getPatientNotes(patId);
		
		return new ResponseEntity<List<NoteDTO>>(noteDTOs, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}", consumes = {"application/json"})
	public ResponseEntity<Object> updateNote(
			@PathVariable("id") @NotBlank String id, 
			@RequestBody @Valid UpdateNoteDTO updateNoteDTO) 
					throws NoteNotFoundException {
		LOGGER.info("Updating note with id=" + id);
		noteService.updateNote(id, updateNoteDTO);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteNote(@PathVariable("id") @NotBlank String id) 
			throws NoteNotFoundException {
		LOGGER.info("Deleting note with id=" + id);
		noteService.deleteNote(id);
		
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
}
