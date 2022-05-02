/**
 * 
 */
package com.tipikae.assessmentservice.assessment;

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
	 * Read all terms.
	 * @return List
	 * @throws TermReaderException
	 */
	List<String> read() throws TermReaderException;
}
