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
 * DTO for update patient.
 * @author tipikae
 * @version 1.0
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePatientDTO {

	@NotBlank
	private String family;
	@NotBlank
	private String given;
	@NotNull
	private LocalDate dob;
	@NotBlank
	private char sex;
	@NotBlank
	private String address;
	@NotBlank
	private String phone;

}
