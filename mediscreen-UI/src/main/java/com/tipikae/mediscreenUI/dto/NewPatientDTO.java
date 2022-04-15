/**
 * 
 */
package com.tipikae.mediscreenUI.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	private LocalDate dob;
	@NotNull(message = "Sex must not be empty.")
	private char sex;
	@NotBlank(message = "Address must not be empty.")
	private String address;
	@NotBlank(message = "Phone number must not be empty.")
	private String phone;
}
