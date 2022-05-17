/**
 * 
 */
package com.tipikae.noteservice.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.tipikae.noteservice.model.Note;

/**
 * Note and NoteDTO converter.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class ConverterNoteDTOImpl implements IConverterNoteDTO {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Note convertNewNoteDTOToNote(NewNoteDTO newNoteDTO) {
		Note note = new Note();
		note.setE(newNoteDTO.getE());
		note.setPatId(newNoteDTO.getPatId());
		
		return note;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Note convertUpdateNoteDTOToNote(UpdateNoteDTO updateNoteDTO, Note note) {
		note.setE(updateNoteDTO.getE());
		
		return note;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public NoteDTO convertNoteToNoteDTO(Note note) {
		NoteDTO noteDTO = new NoteDTO();
		noteDTO.setDate(note.getDate());
		noteDTO.setE(note.getE());
		noteDTO.setId(note.getId());
		noteDTO.setPatId(note.getPatId());
		
		return noteDTO;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<NoteDTO> convertNotesToNoteDTOs(List<Note> notes) {
		return notes.stream()
				.map(note -> convertNoteToNoteDTO(note))
				.collect(Collectors.toList());
	}

}
