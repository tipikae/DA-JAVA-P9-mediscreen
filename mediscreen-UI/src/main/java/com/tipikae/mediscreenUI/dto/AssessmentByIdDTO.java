/**
 * 
 */
package com.tipikae.mediscreenUI.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** Assessment by id DTO.
 * @author tipikae
 * @version 1.0
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentByIdDTO {
	@NotNull
	@Positive
	private long patId;
}
