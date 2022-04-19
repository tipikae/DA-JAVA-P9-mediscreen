/**
 * 
 */
package com.tipikae.mediscreenUI.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for new Patient.
 * @author tipikae
 * @version 1.0
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPatientDTO {

	@NotBlank(message = "Lastname must not be empty.")
	private String family;
	
	@NotBlank(message = "Firstname must not be empty.")
	private String given;
	
	@NotNull(message = "Birthdate must not be empty.")
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past
	private LocalDate dob;
	
	@NotNull(message = "Sex must not be empty.")
	private char sex;
	
	@NotBlank(message = "Address must not be empty.")
	private String address;
	
	@NotBlank(message = "Phone number must not be empty.")
	private String phone;
}
