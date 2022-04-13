/**
 * 
 */
package com.tipikae.patientservice.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Patient model.
 * @author tipikae
 * @version 1.0
 *
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String family;
	private String given;
	private LocalDate dob;
	private char sex;
	private String address;
	private String phone;
}
