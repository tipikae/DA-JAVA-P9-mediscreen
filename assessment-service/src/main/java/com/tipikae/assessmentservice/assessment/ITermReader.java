/**
 * 
 */
package com.tipikae.assessmentservice.assessment;

import java.util.List;

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
