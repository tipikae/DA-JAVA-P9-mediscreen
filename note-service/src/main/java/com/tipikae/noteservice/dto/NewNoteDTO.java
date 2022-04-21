/**
 * 
 */
package com.tipikae.noteservice.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

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
	
	@NotNull(message = "Date must not be null.")
	@PastOrPresent
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date;
	
	@NotBlank(message = "Note must not be empty.")
	private String e;
}
