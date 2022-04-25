/**
 * 
 */
package com.tipikae.assessmentservice.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Assessment by family name DTO.
 * @author tipikae
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssessmentByFamilyDTO {

	@NotBlank
	private String familyName;
}
