/**
 * 
 */
package com.tipikae.noteservice.dto;

import java.util.List;

import com.tipikae.noteservice.model.Note;

/**
 * Interface for converting Note and DTO.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IConverterNoteDTO {
	
	/**
	 * Convert a NewNoteDTO to a Note.
	 * @param newNoteDTO NewNoteDTO
	 * @return Note
	 */
	Note convertNewNoteDTOToNote(NewNoteDTO newNoteDTO);
	
	/**
	 * Convert an UpdateNoteDTO to a Note.
	 * @param updateNoteDTO UpdateNoteDTO
	 * @param note Note
	 * @return Note
	 */
	Note convertUpdateNoteDTOToNote(UpdateNoteDTO updateNoteDTO, Note note);

	/**
	 * Convert Note to NoteDTO.
	 * @param note Note
	 * @return NoteDTO
	 */
	NoteDTO convertNoteToNoteDTO(Note note);
	
	/**
	 * Convert a Note list to a NoteDTO list.
	 * @param notes List
	 * @return List
	 */
	List<NoteDTO> convertNotesToNoteDTOs(List<Note> notes);
}
