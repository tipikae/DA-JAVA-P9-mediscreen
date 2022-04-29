/**
 * 
 */
package com.tipikae.assessmentservice.client;

import java.util.List;

import com.tipikae.assessmentservice.exception.BadRequestException;
import com.tipikae.assessmentservice.exception.HttpClientException;
import com.tipikae.assessmentservice.model.Note;

import feign.Param;
import feign.RequestLine;

/**
 * Feign client for note-service.
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
}
