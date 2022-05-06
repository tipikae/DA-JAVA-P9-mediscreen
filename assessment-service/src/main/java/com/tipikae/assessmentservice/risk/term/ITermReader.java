/**
 * 
 */
package com.tipikae.assessmentservice.risk.term;

import java.util.List;

import com.tipikae.assessmentservice.exception.TermReaderException;

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
	 * @throws TermReaderException
	 */
	List<String> getTerms() throws TermReaderException;
}
