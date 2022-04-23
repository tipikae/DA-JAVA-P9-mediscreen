package com.tipikae.mediscreenUI.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * New note DTO.
 * @author tipikae
 * @version 1.0
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewNoteDTO {

	@NotNull(message = "PatientId must not be null.")
	@Positive(message = "PatientId must be strictly positive.")
	private long patId;
	
	@NotBlank(message = "Note must not be empty.")
	private String e;
}
