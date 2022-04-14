/**
 * 
 */
package com.tipikae.mediscreenUI.model;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Patient model.
 * @author tipikae
 * @version 1.0
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

	private String family;
	private String given;
	private LocalDate dob;
	private char sex;
	private String address;
	private String phone;
}
