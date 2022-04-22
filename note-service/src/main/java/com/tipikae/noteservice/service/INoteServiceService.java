/**
 * 
 */
package com.tipikae.noteservice.service;

import java.util.List;

import com.tipikae.noteservice.dto.NewNoteDTO;
import com.tipikae.noteservice.dto.NoteDTO;
import com.tipikae.noteservice.dto.UpdateNoteDTO;
import com.tipikae.noteservice.exception.NoteNotFoundException;

/**
 * Note Service methods.
 * @author tipikae
 * @version 1.0
 *
 */
public interface INoteServiceService {

	/**
	 * Add a new note.
	 * @param newNoteDTO NewNoteDTO
	 * @return NoteDTO
	 */
	NoteDTO addNote(NewNoteDTO newNoteDTO);
	
	/**
	 * Get a note by its id.
	 * @param id String
	 * @return NoteDTO
	 * @throws NoteNotFoundException
	 */
	NoteDTO getNoteById(String id) throws NoteNotFoundException;
	
	/**
	 * Get all patient's notes.
	 * @param patId long
	 * @return List
	 */
	List<NoteDTO> getPatientNotes(long patId);
	
	/**
	 * Update a note.
	 * @param id String
	 * @param updateNoteDTO UpdateNoteDTO
	 * @throws NoteNotFoundException
	 */
	void updateNote(String id, UpdateNoteDTO updateNoteDTO) throws NoteNotFoundException;
	
	/**
	 * Delete a note by its id.
	 * @param id String
	 * @throws NoteNotFoundException
	 */
	void deleteNote(String id) throws NoteNotFoundException;
}
