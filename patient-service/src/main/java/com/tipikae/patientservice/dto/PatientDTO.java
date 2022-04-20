/**
 * 
 */
package com.tipikae.patientservice.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Patient DTO.
 * @author tipikae
 * @version 1.0
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

	private long id;
	private String family;
	private String given;
	private LocalDate dob;
	private char sex;
	private String address;
	private String phone;
}
