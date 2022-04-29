/**
 * 
 */
package com.tipikae.assessmentservice.converterDTO;

import org.springframework.stereotype.Component;

import com.tipikae.assessmentservice.dto.AssessmentDTO;
import com.tipikae.assessmentservice.model.Assessment;

/**
 * Converter Assessment DTO.
 * @author tipikae
 * @version 1.0
 *
 */
@Component
public class ConverterAssessmentDTOImpl implements IConverterAssessmentDTO {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public AssessmentDTO convertModelToDTO(Assessment assessment) {
		AssessmentDTO assessmentDTO = new AssessmentDTO();
		assessmentDTO.setMessage(assessment.getMessage());
		
		return assessmentDTO;
	}

}
