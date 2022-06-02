/**
 * 
 */
package com.tipikae.mediscreenUI.service;

import java.util.List;

import com.tipikae.mediscreenUI.dto.NewNoteDTO;
import com.tipikae.mediscreenUI.dto.UpdateNoteDTO;
import com.tipikae.mediscreenUI.exception.BadRequestException;
import com.tipikae.mediscreenUI.exception.HttpClientException;
import com.tipikae.mediscreenUI.exception.NotFoundException;
import com.tipikae.mediscreenUI.model.Note;

/**
 * Note service.
 * @author tipikae
 * @version 1.0
 *
 */
public interface INoteService {

	/**
	 * Get all patient's notes.
	 * @param patId long
	 * @return List
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	List<Note> getPatientNotes(long patId) 
			throws BadRequestException, HttpClientException;

	/**
	 * Get a note.
	 * @param id String
	 * @return Note
	 * @throws NotFoundException
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	Note getNote(String id) 
			throws NotFoundException, BadRequestException, HttpClientException;
	
	/**
	 * Add a note.
	 * @param newNoteDTO NewNoteDTO
	 * @return Note
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	Note addNote(NewNoteDTO newNoteDTO) 
			throws BadRequestException, HttpClientException;

	/**
	 * Update a note.
	 * @param id String
	 * @param updateNoteDTO
	 * @throws NotFoundException
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	void updateNote(String id, UpdateNoteDTO updateNoteDTO) 
			throws NotFoundException, BadRequestException, HttpClientException;

	/**
	 * Delete a note.
	 * @param id String
	 * @throws NotFoundException
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	void deleteNote(String id) 
			throws NotFoundException, BadRequestException, HttpClientException;
}
