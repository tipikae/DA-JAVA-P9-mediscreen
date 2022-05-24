/**
 * 
 */
package com.tipikae.mediscreenUI.client;

import java.util.List;

import com.tipikae.mediscreenUI.dto.NewNoteDTO;
import com.tipikae.mediscreenUI.dto.UpdateNoteDTO;
import com.tipikae.mediscreenUI.exception.BadRequestException;
import com.tipikae.mediscreenUI.exception.HttpClientException;
import com.tipikae.mediscreenUI.exception.NotFoundException;
import com.tipikae.mediscreenUI.model.Note;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * Note service Feign client.
 * @author tipikae
 * @version 1.0
 *
 */
public interface INoteServiceClient {

	/**
	 * Get all patient's notes.
	 * @param patId long
	 * @return List
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	@RequestLine("GET /notes/search?patId={patId}")
	List<Note> getPatientNotes(@Param("patId") long patId) 
			throws BadRequestException, HttpClientException;

	/**
	 * Get a note.
	 * @param id String
	 * @return Note
	 * @throws NotFoundException
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	@RequestLine("GET /notes/id/{id}")
	Note getNote(@Param("id") String id) 
			throws NotFoundException, BadRequestException, HttpClientException;
	
	/**
	 * Add a note.
	 * @param newNoteDTO NewNoteDTO
	 * @return Note
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	@RequestLine("POST /notes/")
    @Headers("Content-Type: application/json")
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
	@RequestLine("PUT /notes/{id}")
    @Headers("Content-Type: application/json")
	void updateNote(@Param("id") String id, UpdateNoteDTO updateNoteDTO) 
			throws NotFoundException, BadRequestException, HttpClientException;

	/**
	 * Delete a note.
	 * @param id String
	 * @throws NotFoundException
	 * @throws BadRequestException
	 * @throws HttpClientException
	 */
	@RequestLine("DELETE /notes/{id}")
	void deleteNote(@Param("id") String id) 
			throws NotFoundException, BadRequestException, HttpClientException;
}
