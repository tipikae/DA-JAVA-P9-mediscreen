/**
 * 
 */
package com.tipikae.assessmentservice.risk.term;

import java.util.List;

/**
 * Terms storage reader.
 * @author tipikae
 * @author tipikae 1.0
 *
 */
public interface ITermReader {

	/**
	 * Get all terms.
	 * @return List
	 */
	List<String> getTerms();
}
