/**
 * 
 */
package com.tipikae.mediscreenUI.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for update patient.
 * @author tipikae
 * @version 1.0
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePatientDTO {

	@NotBlank(message = "Lastname must not be empty.")
	private String family;
	
	@NotBlank(message = "Firstname must not be empty.")
	private String given;
	
	@NotNull(message = "Birthdate must not be empty.")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dob;
	
	@NotNull(message = "Sex must not be empty.")
	private char sex;
	
	@NotBlank(message = "Address must not be empty.")
	private String address;
	
	@NotBlank(message = "Phone number must not be empty.")
	private String phone;

}
