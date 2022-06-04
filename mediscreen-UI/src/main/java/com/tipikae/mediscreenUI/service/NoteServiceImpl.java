/**
 * 
 */
package com.tipikae.mediscreenUI.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.tipikae.mediscreenUI.dto.NewNoteDTO;
import com.tipikae.mediscreenUI.dto.UpdateNoteDTO;
import com.tipikae.mediscreenUI.exception.BadRequestException;
import com.tipikae.mediscreenUI.exception.ClientErrorHandler;
import com.tipikae.mediscreenUI.exception.HttpClientException;
import com.tipikae.mediscreenUI.exception.NotFoundException;
import com.tipikae.mediscreenUI.model.Note;
import com.tipikae.mediscreenUI.util.HttpUtility;

/**
 * Note service client.
 * @author tipikae
 * @version 1.0
 *
 */
@Service
public class NoteServiceImpl implements INoteService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NoteServiceImpl.class);
	private static final String ROOT = "/note";
	
	@Autowired
	private HttpUtility httpUtility;

	@Value(value = "${proxy.url:}")
	private String proxyUrl;
	private RestTemplate restTemplate;

	@Autowired
	public NoteServiceImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder
		          .errorHandler(new ClientErrorHandler())
		          .build();
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Note> getPatientNotes(long patId) throws BadRequestException, HttpClientException {
		LOGGER.debug("getPatientNotes: patientId=" + patId);
		String url = proxyUrl + ROOT + "/notes/search?patId={patId}";
		Map<String, Object> map = new HashMap<>();
		map.put("patId", patId);
		
		return restTemplate.exchange(
				url, HttpMethod.GET, httpUtility.getHttpEntity(), List.class, map).getBody();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Note getNote(String id) throws NotFoundException, BadRequestException, HttpClientException {
		LOGGER.debug("getNote: id=" + id);
		String url = proxyUrl + ROOT + "/notes/id/{id}";
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		
		return restTemplate.exchange(
				url, HttpMethod.GET, httpUtility.getHttpEntity(), Note.class, map).getBody();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Note addNote(NewNoteDTO newNoteDTO) throws BadRequestException, HttpClientException {
		LOGGER.debug("addNote: patientId=" + newNoteDTO.getPatId());
		String url = proxyUrl + ROOT + "/notes/";
		HttpEntity<NewNoteDTO> entity = new HttpEntity<>(newNoteDTO, httpUtility.getAuthHeadersJson());
		
		return restTemplate.exchange(
				url, HttpMethod.POST, entity, Note.class, new HashMap<>()).getBody();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateNote(String id, UpdateNoteDTO updateNoteDTO)
			throws NotFoundException, BadRequestException, HttpClientException {
		LOGGER.debug("updateNote: id=" + id);
		String url = proxyUrl + ROOT + "/notes/{id}";
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		HttpEntity<UpdateNoteDTO> entity = new HttpEntity<>(updateNoteDTO, httpUtility.getAuthHeadersJson());
		
		restTemplate.exchange(url, HttpMethod.PUT, entity, Void.class, map);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteNote(String id) throws NotFoundException, BadRequestException, HttpClientException {
		LOGGER.debug("deleteNote: id=" + id);
		String url = proxyUrl + ROOT + "/notes/{id}";
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		
		restTemplate.exchange(url, HttpMethod.DELETE, httpUtility.getHttpEntity(), Void.class, map);
	}

}
