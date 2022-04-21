/**
 * 
 */
package com.tipikae.noteservice.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Note model.
 * @author tipikae
 * @version 1.0
 *
 */
@Document(collection = "notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note {

	@Id
	private String id;
	private long patId;
	private LocalDate date;
	private String e;
}
