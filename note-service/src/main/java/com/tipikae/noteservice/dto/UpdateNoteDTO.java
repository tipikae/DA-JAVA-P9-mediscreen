/**
 * 
 */
package com.tipikae.noteservice.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Update Note DTO.
 * @author tipikae
 * @version 1.0
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNoteDTO {

	@NotBlank(message = "Note must not be empty.")
	private String e;
}
