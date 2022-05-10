/**
 * 
 */
package com.tipikae.assessmentservice.dto;

/**
 * Converter DTO generic.
 * @author tipikae
 * @version 1.0
 *
 */
public interface IConverterDTO<M, D> {

	/**
	 * Convert an Assessment to an AssessmentDTO.
	 * @param model M
	 * @return D
	 */
	D convertModelToDTO(M model);
}
