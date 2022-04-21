/**
 * 
 */
package com.tipikae.noteservice.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Note DTO used as service response.
 * @author tipikae
 * @version 1.0
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDTO {
	
	private String id;
	private long patId;
	private LocalDate date;
	private String e;
}
