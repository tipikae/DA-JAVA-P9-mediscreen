/**
 * 
 */
package com.tipikae.noteservice.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tipikae.noteservice.dto.IConverterNoteDTO;
import com.tipikae.noteservice.dto.NewNoteDTO;
import com.tipikae.noteservice.dto.NoteDTO;
import com.tipikae.noteservice.dto.UpdateNoteDTO;
import com.tipikae.noteservice.exception.NoteNotFoundException;
import com.tipikae.noteservice.model.Note;
import com.tipikae.noteservice.repository.INoteServiceRepository;

/**
 * Note service.
 * @author tipikae
 * @version 1.0
 *
 */
@Service
public class NoteServiceServiceImpl implements INoteServiceService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NoteServiceServiceImpl.class);
	
	@Autowired
	private INoteServiceRepository noteRepository;
	
	@Autowired
	private IConverterNoteDTO noteConverter;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NoteDTO addNote(NewNoteDTO newNoteDTO) {
		LOGGER.debug("addNote: patId=" + newNoteDTO.getPatId());
		Note note = noteConverter.convertNewNoteDTOToNote(newNoteDTO);
		note.setDate(LocalDate.now());
		
		return noteConverter.convertNoteToNoteDTO(
				noteRepository.save(note));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NoteDTO getNoteById(String id) throws NoteNotFoundException {
		LOGGER.debug("getNoteById: id=" + id);
		Optional<Note> optional = noteRepository.findById(id);
		if(!optional.isPresent()) {
			LOGGER.debug("getNoteById: note with id=" + id + " not found.");
			throw new NoteNotFoundException("Note with id=" + id + " not found.");
		}
		
		return noteConverter.convertNoteToNoteDTO(optional.get());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<NoteDTO> getPatientNotes(long patId) {
		LOGGER.debug("getPatientNotes: patId=" + patId);
		return noteConverter.convertNotesToNoteDTOs(noteRepository.findByPatIdOrderByDateDesc(patId));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateNote(String id, UpdateNoteDTO updateNoteDTO) throws NoteNotFoundException {
		LOGGER.debug("updateNote: id=" + id);
		Optional<Note> optional = noteRepository.findById(id);
		if(!optional.isPresent()) {
			LOGGER.debug("updateNote: note with id=" + id + " not found.");
			throw new NoteNotFoundException("Note with id=" + id + " not found.");
		}
		
		noteRepository.save(noteConverter.convertUpdateNoteDTOToNote(updateNoteDTO, optional.get()));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteNote(String id) throws NoteNotFoundException {
		LOGGER.debug("deleteNote: id=" + id);
		Optional<Note> optional = noteRepository.findById(id);
		if(!optional.isPresent()) {
			LOGGER.debug("deleteNote: note with id=" + id + " not found.");
			throw new NoteNotFoundException("Note with id=" + id + " not found.");
		}

		noteRepository.deleteById(id);
	}

}
