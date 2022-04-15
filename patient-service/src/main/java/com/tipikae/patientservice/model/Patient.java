/**
 * 
 */
package com.tipikae.patientservice.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
@Table(name = "patient")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank
	private String family;
	
	@NotBlank
	private String given;
	
	@NotNull
	private LocalDate dob;
	
	@NotNull
	private char sex;
	
	@NotBlank
	private String address;
	
	@NotBlank
	private String phone;
}
